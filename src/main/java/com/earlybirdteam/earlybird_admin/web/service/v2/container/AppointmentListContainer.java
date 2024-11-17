package com.earlybirdteam.earlybird_admin.web.service.v2.container;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.appointment.AppointmentRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v1.VisitEventLogList;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AppointmentListContainer implements DomainListContainer {

    @Getter
    private List<Appointment> appointments = new ArrayList<>();
    private final AppointmentRepository appointmentRepository;
    private LocalDateTime lastUpdateAt;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final TestDataFilter testDataFilter;

    @PostConstruct
    private void init() {
        updateDataList();
    }

    @Override
    public String getLastUpdateAt() {
        return lastUpdateAt.format(dateTimeFormatter);
    }

    @Override
    public void updateDataList() {
        appointments = appointmentRepository.findAll().stream()
                .filter(a -> testDataFilter.isNotTestData(a.getAppointmentName()))
                .toList();

        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
