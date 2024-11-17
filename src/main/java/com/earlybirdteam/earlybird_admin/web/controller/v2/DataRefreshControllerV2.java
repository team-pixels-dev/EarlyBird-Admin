package com.earlybirdteam.earlybird_admin.web.controller.v2;

import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v1.*;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class DataRefreshControllerV2 {

    private final AppointmentListContainer appointmentListContainer;
    private final FeedbackScoreListContainer feedbackScoreListContainer;
    private final VisitEventLogListContainer visitEventLogListContainer;
    private final FeedbackCommentListContainer feedbackCommentListContainer;
    private final ArriveOnTimeEventLogListContainer arriveOnTimeEventLogListContainer;

    private final List<DomainListContainer> domainListContainers = new ArrayList<>();

    @PostConstruct
    private void init() {
        domainListContainers.add(appointmentListContainer);
        domainListContainers.add(feedbackScoreListContainer);
        domainListContainers.add(visitEventLogListContainer);
        domainListContainers.add(feedbackCommentListContainer);
        domainListContainers.add(arriveOnTimeEventLogListContainer);
    }

    @PostMapping("/refresh")
    public String refreshAll(HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        for (DomainListContainer domainListContainer : domainListContainers) {
            domainListContainer.updateDataList();
        }

        return "redirect:" + referer;
    }
}
