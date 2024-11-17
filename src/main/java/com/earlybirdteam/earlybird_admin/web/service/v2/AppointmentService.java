package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.notification.FcmNotification;
import com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentListContainer appointmentListContainer;
    private final SeasonDate season;

    public Long getNumberOfAppointment() {
        long result = 0L;

        for (LocalDate date = season.getStartDate(); !date.isAfter(season.getEndDate()); date = date.plusDays(1)) {
            result += getNumberOfAppointmentAt(date);
        }

        return result;
    }

    private Long getNumberOfAppointmentAt(LocalDate date) {
        return appointmentListContainer.getAppointments().stream()
                .filter(a -> a.getCreatedAt().toLocalDate().isEqual(date))
                .count();
    }

    public Double getNumberOfAppointmentPerUser() {
        Long numberOfAppointment = getNumberOfAppointment();
        if (numberOfAppointment == 0) {
            return 0.0;
        }
        Long numberOfCreateAppointmentUser = getNumberOfCreateAppointmentUser();
        return (double) Math.round((double) numberOfAppointment / numberOfCreateAppointmentUser * 100) / 100;
    }

    public Long getNumberOfCreateAppointmentUser() {
        return appointmentListContainer.getAppointments().stream()
                .filter(a -> {
                    LocalDate createdDate = a.getCreatedAt().toLocalDate();
                    return !(createdDate.isBefore(season.getStartDate()) || createdDate.isAfter(season.getEndDate()));
                })
                .map(Appointment::getClientId)
                .distinct()
                .count();
    }

    public Long countAppointmentByStatus(NotificationStatus status) {
        return appointmentListContainer.getAppointments().stream()
                .filter(a -> {
                    LocalDate createdDate = a.getCreatedAt().toLocalDate();
                    return !(createdDate.isBefore(season.getStartDate()) || createdDate.isAfter(season.getEndDate()));
                })
                .filter(appointment -> {
                    List<FcmNotification> fcmNotifications = appointment.getFcmNotifications();
                    long numOfNotificationAtTargetStatus = fcmNotifications.stream()
                            .filter(fcmNotification -> fcmNotification.getStatus().equals(status))
                            .count();
                    return numOfNotificationAtTargetStatus > 0;
                })
                .count();
    }

    public Long countNotificationByStatus(NotificationStatus status) {
        return appointmentListContainer.getAppointments().stream()
                .filter(a -> {
                    LocalDate createdDate = a.getCreatedAt().toLocalDate();
                    return !(createdDate.isBefore(season.getStartDate()) || createdDate.isAfter(season.getEndDate()));
                })
                .mapToLong(a -> a.getFcmNotifications().stream()
                        .map(FcmNotification::getStatus)
                        .filter(s -> s.equals(status))
                        .count())
                .sum();
    }
}
