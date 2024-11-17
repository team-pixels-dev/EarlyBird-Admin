package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.web.controller.v1.response.AllFeedbackScoreResponse;
import com.earlybirdteam.earlybird_admin.web.service.v1.FeedbackScoreList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FeedbackScoreController {

    private final FeedbackScoreList feedbackScoreList;

    @GetMapping("/feedback/score")
    public String getAllScore(Model model) {
        List<AllFeedbackScoreResponse> responseList = feedbackScoreList.getFeedbackScores().stream()
                .map(f -> AllFeedbackScoreResponse.builder()
                        .clientId(f.getClientId())
                        .score(f.getScore())
                        .createdTimeAtClient(f.getCreatedTimeAtClient().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();

        model.addAttribute("responseList", responseList);
        model.addAttribute("dataLastUpdateAt", feedbackScoreList.getLastUpdateAt());

        return "feedback/score";
    }
}
