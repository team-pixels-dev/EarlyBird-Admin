package com.earlybirdteam.earlybird_admin.web.controller.v1.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AllVisitEventLogResponse {

    private String clientId;
    private String day0; // 2024-11-01
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
}
