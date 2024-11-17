package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLog;
import com.earlybirdteam.earlybird_admin.web.controller.v1.response.AllVisitEventLogResponse;
import com.earlybirdteam.earlybird_admin.web.service.v1.VisitEventLogList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class VisitEventController {

    private final VisitEventLogList visitEventLogList;

    @GetMapping("/visit-event")
    public String visitEvent(Model model) {

        List<AllVisitEventLogResponse> responseList = visitEventLogList.getVisitEventLogs().stream()
                .map(VisitEventLog::getClientId)
                .distinct()
                .map(id -> {
                    List<Boolean> dayVisitBoolByClientId = visitEventLogList.getDayVisitBoolByClientId(id);

                    return AllVisitEventLogResponse.builder()
                        .clientId(id)
                            .day0(dayVisitBoolByClientId.get(0) ? "Y" : "-")
                            .day1(dayVisitBoolByClientId.get(1) ? "Y" : "-")
                            .day2(dayVisitBoolByClientId.get(2) ? "Y" : "-")
                            .day3(dayVisitBoolByClientId.get(3) ? "Y" : "-")
                            .day4(dayVisitBoolByClientId.get(4) ? "Y" : "-")
                            .day5(dayVisitBoolByClientId.get(5) ? "Y" : "-")
                        .build();
                })
                .toList();

        model.addAttribute("responseList", responseList);
        model.addAttribute("dataLastUpdateAt", visitEventLogList.getLastUpdateAt());

        return "visit-event/visit-event";

    }
}
