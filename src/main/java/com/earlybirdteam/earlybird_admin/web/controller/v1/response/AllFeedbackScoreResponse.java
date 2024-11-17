package com.earlybirdteam.earlybird_admin.web.controller.v1.response;

import com.earlybirdteam.earlybird_admin.domain.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AllFeedbackScoreResponse {

    private Integer score;
    private String clientId;
    private String createdTimeAtClient;
}
