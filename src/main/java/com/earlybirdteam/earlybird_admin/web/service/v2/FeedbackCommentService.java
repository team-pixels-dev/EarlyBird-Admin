package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.web.service.v2.container.FeedbackCommentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class FeedbackCommentService {

    private final FeedbackCommentListContainer feedbackCommentListContainer;
    private final SeasonDate season;

    public Long getTotalCount() {
        return feedbackCommentListContainer.getFeedbackComments().stream()
                .filter(f -> {
                    LocalDate createdDate = f.getCreatedAt().toLocalDate();
                    return !(createdDate.isBefore(season.getStartDate()) || createdDate.isAfter(season.getEndDate()));
                })
                .count();
    }
}
