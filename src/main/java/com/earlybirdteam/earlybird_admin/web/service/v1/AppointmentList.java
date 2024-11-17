package com.earlybirdteam.earlybird_admin.web.service.v1;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.appointment.AppointmentRepository;
import com.earlybirdteam.earlybird_admin.domain.notification.FcmNotification;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AppointmentList
        implements DomainListContainer {

    private List<Appointment> appointments = new ArrayList<>();
    private final AppointmentRepository appointmentRepository;
    private LocalDateTime lastUpdateAt;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final VisitEventLogList visitEventLogList;

    @PostConstruct
    private void init() {
        appointments = appointmentRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public String getLastUpdateAt() {
        return lastUpdateAt.format(dateTimeFormatter);
    }

    public void updateDataList() {
        appointments = appointmentRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Integer getNumberOfAppointment() {
        return appointments.size();
    }

    public Double getNumberOfAppointmentPerUser() {
        if (getNumberOfAppointment() == 0) {
            return 0.0;
        }
        return (double) Math.round(Double.valueOf(getNumberOfAppointment()) / Double.valueOf(getNumberOfClientId()) * 100) / 100;
    }

    public Long getNumberOfClientId() {
        return appointments.stream()
                .map(Appointment::getClientId)
                .distinct()
                .count();
    }

    public Long getNumberOfAppointmentModify() {
        return appointments.stream()
                .filter(appointment -> {
                    List<FcmNotification> fcmNotifications = appointment.getFcmNotifications();
                    long numOfModified = fcmNotifications.stream()
                            .filter(fcmNotification -> fcmNotification.getStatus().equals(MODIFIED))
                            .count();
                    return numOfModified > 0;
                })
                .count();
    }

    public Long getNumberOfAppointmentDelete() {
        return appointments.stream()
                .filter(appointment -> {
                    List<FcmNotification> fcmNotifications = appointment.getFcmNotifications();
                    long numOfModified = fcmNotifications.stream()
                            .filter(fcmNotification -> fcmNotification.getStatus().equals(CANCELLED))
                            .count();
                    return numOfModified > 0;
                })
                .count();
    }

    public Double getNumberOfModifyPerUser() {
        return (double) Math.round(Double.valueOf(getNumberOfAppointmentModify()) / visitEventLogList.getTotalVisitUserCount() * 100) / 100;
    }

    public Double getNumberOfDeletePerUser() {
        return (double) Math.round(Double.valueOf(getNumberOfAppointmentDelete()) / visitEventLogList.getTotalVisitUserCount() * 100) / 100;
    }

    public Long getSuccessCount() {
        return appointments.stream()
                .mapToLong(a -> a.getFcmNotifications().stream()
                        .map(FcmNotification::getStatus)
                        .filter(s -> s.equals(COMPLETED))
                        .count())
                .sum();
    }

    public Long getFailedCount() {
        return appointments.stream()
                .mapToLong(a -> a.getFcmNotifications().stream()
                        .map(FcmNotification::getStatus)
                        .filter(s -> s.equals(FAILED))
                        .count())
                .sum();
    }

    public Long getPendingCount() {
        return appointments.stream()
                .mapToLong(a -> a.getFcmNotifications().stream()
                        .map(FcmNotification::getStatus)
                        .filter(s -> s.equals(PENDING))
                        .count())
                .sum();
    }
}
