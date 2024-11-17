package com.earlybirdteam.earlybird_admin.web.controller.v1.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AllFeedbackCommentResponse {

    private String comment;
    private String clientId;
    private String createdTimeAtClient;
}
