package com.earlybirdteam.earlybird_admin.web.controller.v2.dashboard;

import com.earlybirdteam.earlybird_admin.web.service.v2.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BetaTestSeason1DashboardController {

    private final AppointmentService appointmentServiceSeason1;
    private final UserCountService userCountServiceSeason1;
    private final AppointmentListContainer appointmentListContainer;
    private final DayRetentionService dayRetentionServiceSeason1;
    private final FeedbackCommentService feedbackCommentServiceSeason1;
    private final FeedbackScoreService feedbackScoreServiceSeason1;
    private final ArriveOnTimeEventLogService arriveOnTimeEventLogServiceSeason1;

    public BetaTestSeason1DashboardController(
            @Qualifier("appointmentServiceBetaTestSeason1") AppointmentService appointmentService,
            @Qualifier("userCountServiceBetaTestSeason1") UserCountService userCountService,
            AppointmentListContainer appointmentListContainer,
            @Qualifier("dayRetentionServiceBetaTestSeason1") DayRetentionService dayRetentionService,
            @Qualifier("feedbackCommentServiceBetaTestSeason1") FeedbackCommentService feedbackCommentService,
            @Qualifier("feedbackScoreServiceBetaTestSeason1") FeedbackScoreService feedbackScoreService,
            @Qualifier("arriveOnTimeEventLogServiceBetaTestSeason1") ArriveOnTimeEventLogService arriveOnTimeEventLogService
    ) {
        this.appointmentServiceSeason1 = appointmentService;
        this.userCountServiceSeason1 = userCountService;
        this.appointmentListContainer = appointmentListContainer;
        this.dayRetentionServiceSeason1 = dayRetentionService;
        this.feedbackCommentServiceSeason1 = feedbackCommentService;
        this.feedbackScoreServiceSeason1 = feedbackScoreService;
        this.arriveOnTimeEventLogServiceSeason1 = arriveOnTimeEventLogService;
    }

    @GetMapping("/dashboard/beta-test/season1")
    public String betaTestSeason1Dashboard(Model model) {

        DashboardModelInitializer modelInitializer = new DashboardModelInitializer(
            "1차 베타테스트",
                appointmentListContainer,
                userCountServiceSeason1,
                arriveOnTimeEventLogServiceSeason1,
                dayRetentionServiceSeason1,
                feedbackCommentServiceSeason1,
                feedbackScoreServiceSeason1,
                appointmentServiceSeason1
        );

        modelInitializer.init(model);

        return "v2/dashboard";
    }
}
