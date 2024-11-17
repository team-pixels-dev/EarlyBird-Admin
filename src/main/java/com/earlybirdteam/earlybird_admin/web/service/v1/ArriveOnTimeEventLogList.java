package com.earlybirdteam.earlybird_admin.web.service.v1;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.log.arrive.ArriveOnTimeEventLog;
import com.earlybirdteam.earlybird_admin.domain.log.arrive.ArriveOnTimeEventLogRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ArriveOnTimeEventLogList implements DomainListContainer {

    private final ArriveOnTimeEventLogRepository arriveOnTimeEventLogRepository;
    private List<ArriveOnTimeEventLog> arriveOnTimeEventLogs;
    private LocalDateTime lastUpdateAt;
    @Getter
    private Map<String, LocalDateTime> clientIdAndFirstArriveOnTimeMap;
    @Getter
    private Map<String, Long> clientIdAndArriveOnTimeCountMap;
    private final VisitEventLogList visitEventLogList;
    private final AppointmentList appointmentList;
    @Getter
    private Double arriveOnTimeAndRecreateAppointmentPercent;

    @PostConstruct
    private void init() {
        arriveOnTimeEventLogs = arriveOnTimeEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        initClientIdAndFirstArriveOnTimeMap();
        initArriveOnTimeAndRecreateAppointmentPercent();
        initClientIdAndArriveOnTimeCountMap();
    }

    private void initClientIdAndFirstArriveOnTimeMap() {
        clientIdAndFirstArriveOnTimeMap = new HashMap<>();

        arriveOnTimeEventLogs.forEach(log -> {
            String clientId = log.getAppointment().getClientId();
            if (clientIdAndFirstArriveOnTimeMap.containsKey(clientId)) {
                LocalDateTime firstArriveOnTime = clientIdAndFirstArriveOnTimeMap.get(clientId);
                LocalDateTime loggingTime = log.getCreatedAt();

                if (loggingTime.isBefore(firstArriveOnTime)) {
                    clientIdAndFirstArriveOnTimeMap.put(clientId, loggingTime);
                }
            } else {
                clientIdAndFirstArriveOnTimeMap.put(clientId, log.getCreatedAt());
            }
        });
    }

    public void initClientIdAndArriveOnTimeCountMap() {
        clientIdAndArriveOnTimeCountMap = new HashMap<>();

        arriveOnTimeEventLogs.forEach(log -> {
            String clientId = log.getAppointment().getClientId();
            if (clientIdAndArriveOnTimeCountMap.containsKey(clientId)) {
                clientIdAndArriveOnTimeCountMap.put(clientId, clientIdAndArriveOnTimeCountMap.get(clientId) + 1);
            } else {
                clientIdAndArriveOnTimeCountMap.put(clientId, 1L);
            }
        });
    }

    private void initArriveOnTimeAndRecreateAppointmentPercent() {
        Long totalVisitUserCount = visitEventLogList.getTotalVisitUserCount();

        long arriveOnTimeAndRecreateAppointmentUserCount = appointmentList.getAppointments().stream()
                .filter(a ->
                        clientIdAndFirstArriveOnTimeMap.containsKey(a.getClientId())
                                && a.getCreatedAt().isAfter(clientIdAndFirstArriveOnTimeMap.get(a.getClientId())))
                .map(Appointment::getClientId)
                .distinct()
                .count();

        arriveOnTimeAndRecreateAppointmentPercent = Math.round((double) arriveOnTimeAndRecreateAppointmentUserCount / totalVisitUserCount * 100 * 100) / 100.0;
    }


    @Override
    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void updateDataList() {
        arriveOnTimeEventLogs = arriveOnTimeEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        initClientIdAndFirstArriveOnTimeMap();
        initArriveOnTimeAndRecreateAppointmentPercent();
        initClientIdAndArriveOnTimeCountMap();
    }
}
