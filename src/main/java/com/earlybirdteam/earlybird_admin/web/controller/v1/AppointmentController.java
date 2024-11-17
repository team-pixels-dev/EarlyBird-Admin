package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.domain.notification.FcmNotification;
import com.earlybirdteam.earlybird_admin.web.controller.v1.response.AllAppointmentResponse;
import com.earlybirdteam.earlybird_admin.web.service.v1.AppointmentList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

import static com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus.*;

@RequiredArgsConstructor
@Controller
public class AppointmentController {

    private final AppointmentList appointmentList;

    @GetMapping("/appointment")
    public String showAllAppointment(Model model) {
        List<AllAppointmentResponse> responseList = appointmentList.getAppointments().stream()
                .map(appointment -> AllAppointmentResponse.builder()
                        .appointmentName(appointment.getAppointmentName())
                        .clientId(appointment.getClientId())
                        .sendSuccessCount(appointment.getFcmNotifications().stream()
                                .filter(f -> f.getStatus().equals(COMPLETED)).count())
                        .sendFailCount(appointment.getFcmNotifications().stream()
                                .filter(f -> f.getStatus().equals(FAILED)).count())
                        .sendPendingCount(appointment.getFcmNotifications().stream()
                                .filter(f -> f.getStatus().equals(PENDING)).count())
                        .appointmentIsModified(appointment.getFcmNotifications().stream()
                                .anyMatch(f -> f.getStatus().equals(MODIFIED)) ? "V" : "-")
                        .appointmentIsDeleted(appointment.getFcmNotifications().stream()
                                .anyMatch(f -> f.getStatus().equals(CANCELLED)) ? "V" : "-")
                        .appointmentCreateTime(appointment.getFcmNotifications().stream()
                                .map(FcmNotification::getCreatedAt)
                                .min(Comparator.naturalOrder()).get())
                        .appointmentModifyTime(appointment.getFcmNotifications().stream()
                                .map(FcmNotification::getUpdatedAt)
                                .max(Comparator.naturalOrder()).get())
                        .build()
                )
                .toList();

        model.addAttribute("responseList", responseList);

        model.addAttribute("dataLastUpdateAt", appointmentList.getLastUpdateAt());

        model.addAttribute("successCount", appointmentList.getSuccessCount());
        model.addAttribute("failedCount", appointmentList.getFailedCount());
        model.addAttribute("pendingCount", appointmentList.getPendingCount());

        return "appointment/appointment";
    }
}
