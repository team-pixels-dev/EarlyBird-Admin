package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.ArriveOnTimeEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.VisitEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ArriveOnTimeEventLogService {

    private final ArriveOnTimeEventLogListContainer arriveOnTimeEventLogListContainer;
    private final UserCountService userCountService;
    private final AppointmentListContainer appointmentListContainer;
    private final SeasonDate season;

    @Getter
    private Map<String, LocalDateTime> clientIdAndFirstArriveOnTimeMap;
    @Getter
    private Map<String, Long> clientIdAndArriveOnTimeCountMap;
    @Getter
    private Double arriveOnTimeAndRecreateAppointmentPercent;

    @PostConstruct
    private void init() {
        initClientIdAndFirstArriveOnTimeMap();
        initClientIdAndArriveOnTimeCountMap();
        initArriveOnTimeAndRecreateAppointmentPercent();
    }

    private void initClientIdAndFirstArriveOnTimeMap() {
        clientIdAndFirstArriveOnTimeMap = new HashMap<>();

        arriveOnTimeEventLogListContainer.getArriveOnTimeEventLogs().stream()
                .filter(log -> season.checkDateIsInSeason(log.getCreatedAt()))
                .forEach(log -> {
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

    private void initClientIdAndArriveOnTimeCountMap() {
        clientIdAndArriveOnTimeCountMap = new HashMap<>();

        arriveOnTimeEventLogListContainer.getArriveOnTimeEventLogs().stream()
                .filter(log -> season.checkDateIsInSeason(log.getCreatedAt()))
                .forEach(log -> {
                    String clientId = log.getAppointment().getClientId();
                    if (clientIdAndArriveOnTimeCountMap.containsKey(clientId)) {
                        clientIdAndArriveOnTimeCountMap.put(clientId, clientIdAndArriveOnTimeCountMap.get(clientId) + 1);
                    } else {
                        clientIdAndArriveOnTimeCountMap.put(clientId, 1L);
                    }
                });
    }

    private void initArriveOnTimeAndRecreateAppointmentPercent() {
        Long totalVisitUserCount = userCountService.getTotalUserCountInSeason();

        long arriveOnTimeAndRecreateAppointmentUserCount = appointmentListContainer.getAppointments().stream()
                .filter(a -> season.checkDateIsInSeason(a.getCreatedAt()))
                .filter(a ->
                        clientIdAndFirstArriveOnTimeMap.containsKey(a.getClientId())
                                && a.getCreatedAt().isAfter(clientIdAndFirstArriveOnTimeMap.get(a.getClientId())))
                .map(Appointment::getClientId)
                .distinct()
                .count();

        arriveOnTimeAndRecreateAppointmentPercent = Math.round((double) arriveOnTimeAndRecreateAppointmentUserCount / totalVisitUserCount * 100 * 100) / 100.0;
    }

}
