package com.earlybirdteam.earlybird_admin.web.service.v2.container;

import com.earlybirdteam.earlybird_admin.domain.feedback.score.FeedbackScore;
import com.earlybirdteam.earlybird_admin.domain.feedback.score.FeedbackScoreRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FeedbackScoreListContainer implements DomainListContainer {

    @Getter
    private List<FeedbackScore> feedbackScores;
    private final FeedbackScoreRepository feedbackScoreRepository;
    private LocalDateTime lastUpdateAt;

    @PostConstruct
    private void init() {
        updateDataList();
    }

    @Override
    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void updateDataList() {
        feedbackScores = feedbackScoreRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
