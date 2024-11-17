package com.earlybirdteam.earlybird_admin.web.controller.v2.dashboard;

import com.earlybirdteam.earlybird_admin.web.service.v2.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BetaTestSeason2DashboardController {

    private final AppointmentService appointmentServiceSeason2;
    private final UserCountService userCountServiceSeason2;
    private final AppointmentListContainer appointmentListContainer;
    private final DayRetentionService dayRetentionServiceSeason2;
    private final FeedbackCommentService feedbackCommentServiceSeason2;
    private final FeedbackScoreService feedbackScoreServiceSeason2;
    private final ArriveOnTimeEventLogService arriveOnTimeEventLogServiceSeason2;

    public BetaTestSeason2DashboardController(
            @Qualifier("appointmentServiceBetaTestSeason2") AppointmentService appointmentService,
            @Qualifier("userCountServiceBetaTestSeason2") UserCountService userCountService,
            AppointmentListContainer appointmentListContainer,
            @Qualifier("dayRetentionServiceBetaTestSeason2") DayRetentionService dayRetentionService,
            @Qualifier("feedbackCommentServiceBetaTestSeason2") FeedbackCommentService feedbackCommentService,
            @Qualifier("feedbackScoreServiceBetaTestSeason2") FeedbackScoreService feedbackScoreService,
            @Qualifier("arriveOnTimeEventLogServiceBetaTestSeason2") ArriveOnTimeEventLogService arriveOnTimeEventLogService
    ) {
        this.appointmentServiceSeason2 = appointmentService;
        this.userCountServiceSeason2 = userCountService;
        this.appointmentListContainer = appointmentListContainer;
        this.dayRetentionServiceSeason2 = dayRetentionService;
        this.feedbackCommentServiceSeason2 = feedbackCommentService;
        this.feedbackScoreServiceSeason2 = feedbackScoreService;
        this.arriveOnTimeEventLogServiceSeason2 = arriveOnTimeEventLogService;
    }

    @GetMapping("/dashboard/beta-test/season2")
    public String betaTestSeason1Dashboard(Model model) {

        DashboardModelInitializer modelInitializer = new DashboardModelInitializer(
                "2차 베타테스트",
                appointmentListContainer,
                userCountServiceSeason2,
                arriveOnTimeEventLogServiceSeason2,
                dayRetentionServiceSeason2,
                feedbackCommentServiceSeason2,
                feedbackScoreServiceSeason2,
                appointmentServiceSeason2
        );

        modelInitializer.init(model);

        return "v2/dashboard";
    }
}
