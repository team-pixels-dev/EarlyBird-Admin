package com.earlybirdteam.earlybird_admin.web.controller.v2.dashboard;

import com.earlybirdteam.earlybird_admin.web.service.v2.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BetaTestSeason3DashboardController {

    private final AppointmentService appointmentService;
    private final UserCountService userCountService;
    private final AppointmentListContainer appointmentListContainer;
    private final DayRetentionService dayRetentionService;
    private final FeedbackCommentService feedbackCommentService;
    private final FeedbackScoreService feedbackScoreService;
    private final ArriveOnTimeEventLogService arriveOnTimeEventLogService;

    public BetaTestSeason3DashboardController(
            @Qualifier("appointmentServiceBetaTestSeason3") AppointmentService appointmentService,
            @Qualifier("userCountServiceBetaTestSeason3") UserCountService userCountService,
            AppointmentListContainer appointmentListContainer,
            @Qualifier("dayRetentionServiceBetaTestSeason3") DayRetentionService dayRetentionService,
            @Qualifier("feedbackCommentServiceBetaTestSeason3") FeedbackCommentService feedbackCommentService,
            @Qualifier("feedbackScoreServiceBetaTestSeason3") FeedbackScoreService feedbackScoreService,
            @Qualifier("arriveOnTimeEventLogServiceBetaTestSeason3") ArriveOnTimeEventLogService arriveOnTimeEventLogService
    ) {
        this.appointmentService = appointmentService;
        this.userCountService = userCountService;
        this.appointmentListContainer = appointmentListContainer;
        this.dayRetentionService = dayRetentionService;
        this.feedbackCommentService = feedbackCommentService;
        this.feedbackScoreService = feedbackScoreService;
        this.arriveOnTimeEventLogService = arriveOnTimeEventLogService;
    }

    @GetMapping("/dashboard/beta-test/season3")
    public String betaTestSeason1Dashboard(Model model) {

        DashboardModelInitializer modelInitializer = new DashboardModelInitializer(
                "3차 베타테스트",
                appointmentListContainer,
                userCountService,
                arriveOnTimeEventLogService,
                dayRetentionService,
                feedbackCommentService,
                feedbackScoreService,
                appointmentService
        );

        modelInitializer.init(model);

        return "v2/dashboard";
    }
}
