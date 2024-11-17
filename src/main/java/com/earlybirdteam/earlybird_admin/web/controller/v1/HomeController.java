package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.web.service.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final AppointmentList appointmentList;
    private final VisitEventLogList visitEventLogList;
    private final FeedbackCommentList feedbackCommentList;
    private final FeedbackScoreList feedbackScoreList;
    private final ArriveOnTimeEventLogList arriveOnTimeEventLogList;

//    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalAppointmentNum", appointmentList.getNumberOfAppointment());
        model.addAttribute("appointmentPerUser", appointmentList.getNumberOfAppointmentPerUser());
        model.addAttribute("userNum", appointmentList.getNumberOfClientId());
        model.addAttribute("modifyCount", appointmentList.getNumberOfAppointmentModify());
        model.addAttribute("modifyPerUser", appointmentList.getNumberOfModifyPerUser());
        model.addAttribute("deleteCount", appointmentList.getNumberOfAppointmentDelete());
        model.addAttribute("deletePerUser", appointmentList.getNumberOfDeletePerUser());
        model.addAttribute("dataLastUpdateAt", appointmentList.getLastUpdateAt());
        model.addAttribute("totalVisitUser", visitEventLogList.getTotalVisitUserCount());
        model.addAttribute("day1UserCount", visitEventLogList.day1UserCount());
        model.addAttribute("day2UserCount", visitEventLogList.day2UserCount());
        model.addAttribute("textFeedbackCount", feedbackCommentList.getTotalCount());
        model.addAttribute("scoreFeedbackCount", feedbackScoreList.getTotalCount());
        model.addAttribute("averageScore", Math.round(feedbackScoreList.getAverageScore() * 100) / 100);
        model.addAttribute("nineToTen", feedbackScoreList.getCountScoreBetween(9, 10));
        model.addAttribute("zeroToSix", feedbackScoreList.getCountScoreBetween(0, 6));
        model.addAttribute("totalCount", feedbackScoreList.getTotalCount());
        model.addAttribute("nineToTenDistinct", feedbackScoreList.getCountScoreWithDistinctUserBetween(9, 10));
        model.addAttribute("zeroToSixDistinct", feedbackScoreList.getCountScoreWithDistinctUserBetween(0, 6));
        model.addAttribute("day1Retention", visitEventLogList.dayRetention(1));
        model.addAttribute("day2Retention", visitEventLogList.dayRetention(2));
        model.addAttribute("day3Retention", visitEventLogList.dayRetention(3));
        model.addAttribute("day4Retention", visitEventLogList.dayRetention(4));
        model.addAttribute("day5Retention", visitEventLogList.dayRetention(5));
        model.addAttribute("arriveOnTimeAndRecreateAppointmentPercent", arriveOnTimeEventLogList.getArriveOnTimeAndRecreateAppointmentPercent());

        return "home";
    }
}
