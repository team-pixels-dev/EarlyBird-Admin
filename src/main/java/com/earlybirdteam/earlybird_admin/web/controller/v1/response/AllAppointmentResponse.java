package com.earlybirdteam.earlybird_admin.web.controller.v1.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AllAppointmentResponse {

    private String clientId;
    private String appointmentName;
    private Long sendSuccessCount;
    private Long sendFailCount;
    private Long sendPendingCount;
    private String appointmentIsModified;
    private String appointmentIsDeleted;
    private LocalDateTime appointmentCreateTime;
    private LocalDateTime appointmentModifyTime;
}
