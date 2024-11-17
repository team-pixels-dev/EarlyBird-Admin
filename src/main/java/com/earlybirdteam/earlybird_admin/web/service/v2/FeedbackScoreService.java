package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.domain.feedback.score.FeedbackScore;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.FeedbackScoreListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedbackScoreService {

    private final FeedbackScoreListContainer feedbackScoreListContainer;
    private final SeasonDate season;

    public Long getTotalCount() {
        return feedbackScoreListContainer.getFeedbackScores().stream()
                .filter(f -> season.checkDateIsInSeason(f.getCreatedAt()))
                .count();
    }

    public Double getAverageScore() {
        double averageScore = feedbackScoreListContainer.getFeedbackScores().stream()
                .filter(f -> season.checkDateIsInSeason(f.getCreatedAt()))
                .mapToInt(FeedbackScore::getScore)
                .average().orElse(0.0);

        return Math.round(averageScore * 100.0) / 100.0;
    }

    public Long getCountScore(int score) {
        return feedbackScoreListContainer.getFeedbackScores().stream()
                .filter(f -> season.checkDateIsInSeason(f.getCreatedAt()))
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
        return feedbackScoreListContainer.getFeedbackScores().stream()
                .filter(f -> season.checkDateIsInSeason(f.getCreatedAt()))
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
}
