package com.earlybirdteam.earlybird_admin.web.service.v2.container;

import com.earlybirdteam.earlybird_admin.domain.feedback.comment.FeedbackComment;
import com.earlybirdteam.earlybird_admin.domain.feedback.comment.FeedbackCommentRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FeedbackCommentListContainer implements DomainListContainer {

    @Getter
    private List<FeedbackComment> feedbackComments = new ArrayList<>();
    private final FeedbackCommentRepository feedbackCommentRepository;
    private LocalDateTime lastUpdateAt;

    @PostConstruct
    private void init() {
        updateDataList();
    }

    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void updateDataList() {
        feedbackComments = feedbackCommentRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
