package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.web.service.*;
import com.earlybirdteam.earlybird_admin.web.service.v1.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class DataRefreshController {

    private final AppointmentList appointmentList;
    private final FeedbackScoreList feedbackScoreList;
    private final VisitEventLogList visitEventLogList;
    private final FeedbackCommentList feedbackCommentList;
    private final ArriveOnTimeEventLogList arriveOnTimeEventLogList;

    private final List<DomainListContainer> domainListContainers = new ArrayList<>();

    @PostConstruct
    private void init() {
        domainListContainers.add(appointmentList);
        domainListContainers.add(feedbackScoreList);
        domainListContainers.add(visitEventLogList);
        domainListContainers.add(feedbackCommentList);
        domainListContainers.add(arriveOnTimeEventLogList);
    }

//    @PostMapping("/refresh")
    public String refreshAll(HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        for (DomainListContainer domainListContainer : domainListContainers) {
            domainListContainer.updateDataList();
        }

        return "redirect:" + referer;
    }
}
