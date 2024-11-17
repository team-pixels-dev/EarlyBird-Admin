package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.web.controller.v1.response.AllArriveOnTimeEventLogResponse;
import com.earlybirdteam.earlybird_admin.web.service.v1.ArriveOnTimeEventLogList;
import com.earlybirdteam.earlybird_admin.web.service.v1.VisitEventLogList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ArriveOnTimeEventController {

    private final ArriveOnTimeEventLogList arriveOnTimeEventLogList;
    private final VisitEventLogList visitEventLogList;

    @GetMapping("/arrive-on-time-event")
    public String arriveOnTimeEvent(Model model) {

        Map<String, LocalDateTime> clientIdAndFirstArriveOnTimeMap = arriveOnTimeEventLogList.getClientIdAndFirstArriveOnTimeMap();
        Map<String, Long> clientIdAndArriveOnTimeCountMap = arriveOnTimeEventLogList.getClientIdAndArriveOnTimeCountMap();

        List<AllArriveOnTimeEventLogResponse> responseList = visitEventLogList.getAllClientIdList().stream()
                .map(clientId -> {

                    String firstArriveOnTime = "-";
                    Long arriveOnTimeCount = 0L;

                    if (clientIdAndArriveOnTimeCountMap.containsKey(clientId)) {
                        firstArriveOnTime = clientIdAndFirstArriveOnTimeMap.get(clientId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        arriveOnTimeCount = clientIdAndArriveOnTimeCountMap.get(clientId);
                    }

                    return AllArriveOnTimeEventLogResponse.builder()
                            .clientId(clientId)
                            .firstArriveOnTime(firstArriveOnTime)
                            .arriveOnTimeCount(arriveOnTimeCount)
                            .build();
                })
                .toList();

        model.addAttribute("responseList", responseList);
        model.addAttribute("dataLastUpdateAt", arriveOnTimeEventLogList.getLastUpdateAt());

        return "arrive-on-time-event/arrive-on-time-event";
    }
}
