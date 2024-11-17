package com.earlybirdteam.earlybird_admin.web.controller.v2.dashboard;

import com.earlybirdteam.earlybird_admin.web.service.v2.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllTimeDashboardController {

    private final AppointmentService appointmentServiceAllTime;
    private final UserCountService userCountService;
    private final DayRetentionService dayRetentionServiceAllTime;
    private final FeedbackCommentService feedbackCommentServiceAllTime;
    private final FeedbackScoreService feedbackScoreServiceAllTime;
    private final ArriveOnTimeEventLogService arriveOnTimeEventLogServiceAllTime;

    private final AppointmentListContainer appointmentListContainer;


    public AllTimeDashboardController(
            @Qualifier("appointmentServiceAllTime") AppointmentService appointmentServiceAllTime,
            @Qualifier("userCountServiceAllTime") UserCountService userCountService,
            @Qualifier("dayRetentionServiceAllTime") DayRetentionService dayRetentionService,
            @Qualifier("feedbackCommentServiceAllTime") FeedbackCommentService feedbackCommentService,
            @Qualifier("feedbackScoreServiceAllTime") FeedbackScoreService feedbackScoreService,
            @Qualifier("arriveOnTimeEventLogServiceAllTime") ArriveOnTimeEventLogService arriveOnTimeEventLogService,
            AppointmentListContainer appointmentListContainer
    ) {
        this.appointmentServiceAllTime = appointmentServiceAllTime;
        this.userCountService = userCountService;
        this.appointmentListContainer = appointmentListContainer;
        this.dayRetentionServiceAllTime = dayRetentionService;
        this.feedbackCommentServiceAllTime = feedbackCommentService;
        this.feedbackScoreServiceAllTime = feedbackScoreService;
        this.arriveOnTimeEventLogServiceAllTime = arriveOnTimeEventLogService;
    }

    @GetMapping("/dashboard")
    public String allTimeDashboard(Model model) {

        DashboardModelInitializer modelInitializer = new DashboardModelInitializer(
                "전체 기간",
                appointmentListContainer,
                userCountService,
                arriveOnTimeEventLogServiceAllTime,
                dayRetentionServiceAllTime,
                feedbackCommentServiceAllTime,
                feedbackScoreServiceAllTime,
                appointmentServiceAllTime
        );

        modelInitializer.init(model);

        return "v2/dashboard";

    }
}
