package com.earlybirdteam.earlybird_admin.web.controller.v2.dashboard;

import com.earlybirdteam.earlybird_admin.web.service.v2.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus.CANCELLED;
import static com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus.MODIFIED;

@RequiredArgsConstructor
public class DashboardModelInitializer {

    private final String seasonName;
    private final AppointmentListContainer appointmentListContainer;
    private final UserCountService userCountService;
    private final ArriveOnTimeEventLogService arriveOnTimeEventLogService;
    private final DayRetentionService dayRetentionService;
    private final FeedbackCommentService feedbackCommentService;
    private final FeedbackScoreService feedbackScoreService;
    private final AppointmentService appointmentService;

    public void init(Model model) {
        model.addAttribute("seasonName", seasonName);
        model.addAttribute("dataLastUpdateAt", appointmentListContainer.getLastUpdateAt());

        model.addAttribute("totalVisitUser", userCountService.getTotalUserCountInSeason());
        model.addAttribute("userNum", appointmentService.getNumberOfCreateAppointmentUser());
        model.addAttribute("totalAppointmentNum", appointmentService.getNumberOfAppointment());
        model.addAttribute("appointmentPerUser", appointmentService.getNumberOfAppointmentPerUser());
        model.addAttribute("modifyCount", appointmentService.countAppointmentByStatus(MODIFIED));
        model.addAttribute("deleteCount", appointmentService.countAppointmentByStatus(CANCELLED));

        model.addAttribute("textFeedbackCount", feedbackCommentService.getTotalCount());
        model.addAttribute("scoreFeedbackCount", feedbackScoreService.getTotalCount());
        model.addAttribute("averageScore", feedbackScoreService.getAverageScore());
        model.addAttribute("nineToTen", feedbackScoreService.getCountScoreBetween(9, 10));
        model.addAttribute("zeroToSix", feedbackScoreService.getCountScoreBetween(0, 6));
        model.addAttribute("nineToTenDistinct", feedbackScoreService.getCountScoreWithDistinctUserBetween(9, 10));
        model.addAttribute("zeroToSixDistinct", feedbackScoreService.getCountScoreWithDistinctUserBetween(0, 6));

        model.addAttribute("userCountsInSeason", userCountService.getUserCountsInSeason());
        model.addAttribute("dayRetentionsInSeason", dayRetentionService.getDayRetentionsInSeason());

        model.addAttribute("arriveOnTimeAndRecreateAppointmentPercent", arriveOnTimeEventLogService.getArriveOnTimeAndRecreateAppointmentPercent());

    }
}
