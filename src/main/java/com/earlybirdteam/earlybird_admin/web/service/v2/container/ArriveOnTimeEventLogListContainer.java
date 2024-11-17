package com.earlybirdteam.earlybird_admin.web.service.v2.container;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.log.arrive.ArriveOnTimeEventLog;
import com.earlybirdteam.earlybird_admin.domain.log.arrive.ArriveOnTimeEventLogRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v1.AppointmentList;
import com.earlybirdteam.earlybird_admin.web.service.v1.VisitEventLogList;
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
public class ArriveOnTimeEventLogListContainer implements DomainListContainer {

    private final ArriveOnTimeEventLogRepository arriveOnTimeEventLogRepository;
    @Getter
    private List<ArriveOnTimeEventLog> arriveOnTimeEventLogs;
    private LocalDateTime lastUpdateAt;
    private final VisitEventLogList visitEventLogList;
    private final AppointmentList appointmentList;

    @PostConstruct
    private void init() {
        arriveOnTimeEventLogs = arriveOnTimeEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    @Override
    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void updateDataList() {
        arriveOnTimeEventLogs = arriveOnTimeEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
