package com.earlybirdteam.earlybird_admin.web.controller.v1;

import com.earlybirdteam.earlybird_admin.web.controller.v1.response.AllFeedbackCommentResponse;
import com.earlybirdteam.earlybird_admin.web.service.v1.FeedbackCommentList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FeedbackCommentController {

    private final FeedbackCommentList feedbackCommentList;

    @GetMapping("/feedback/comment")
    public String getAllScore(Model model) {

        List<AllFeedbackCommentResponse> responseList = feedbackCommentList.getFeedbackComments().stream()
                .map(f -> AllFeedbackCommentResponse.builder()
                        .clientId(f.getClientId())
                        .comment(f.getComment())
                        .createdTimeAtClient(f.getCreatedTimeAtClient().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();

        model.addAttribute("responseList", responseList);
        model.addAttribute("dataLastUpdateAt", feedbackCommentList.getLastUpdateAt());

        return "feedback/comment";
    }
}
