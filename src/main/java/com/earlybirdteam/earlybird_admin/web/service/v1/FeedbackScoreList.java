package com.earlybirdteam.earlybird_admin.web.service.v1;

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
public class FeedbackScoreList implements DomainListContainer {

    @Getter
    private List<FeedbackScore> feedbackScores;
    private final FeedbackScoreRepository feedbackScoreRepository;
    private LocalDateTime lastUpdateAt;

    @PostConstruct
    private void init() {
        feedbackScores = feedbackScoreRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Integer getTotalCount() {
        return feedbackScores.size();
    }

    public Double getAverageScore() {
        return feedbackScores.stream()
                .mapToInt(FeedbackScore::getScore)
                .average().orElse(0.0);
    }

    public Long getCountScore(int score) {
        return feedbackScores.stream()
                .filter(f -> f.getScore().equals(score))
                .count();
    }

    public Long getCountScoreBetween(int start, int end) {
        Long result = 0L;
        for (int i = start; i <= end; i++) {
            result += getCountScore(i);
        }
        return result;
    }

    public Long getCountScoreWithDistinctUser(int score) {
        return feedbackScores.stream()
                .filter(f -> f.getScore().equals(score))
                .map(FeedbackScore::getClientId)
                .distinct()
                .count();
    }

    public Long getCountScoreWithDistinctUserBetween(int start, int end) {
        Long result = 0L;
        for (int i = start; i <= end; i++) {
            result += getCountScoreWithDistinctUser(i);
        }
        return result;
    }

    public void updateDataList() {
        feedbackScores = feedbackScoreRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
