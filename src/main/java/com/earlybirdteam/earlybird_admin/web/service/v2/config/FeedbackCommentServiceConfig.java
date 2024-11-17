package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.FeedbackCommentService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.FeedbackCommentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season3.BetaTestSeason3Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackCommentServiceConfig {

    @Qualifier("feedbackCommentServiceAllTime")
    @Bean
    public FeedbackCommentService feedbackCommentServiceAllTime(FeedbackCommentListContainer container, AllTimeDate date) {
        return new FeedbackCommentService(container, date);
    }

    @Qualifier("feedbackCommentServiceBetaTestSeason1")
    @Bean
    public FeedbackCommentService feedbackCommentServiceSeason1(FeedbackCommentListContainer container, BetaTestSeason1Date date) {
        return new FeedbackCommentService(container, date);
    }

    @Qualifier("feedbackCommentServiceBetaTestSeason2")
    @Bean
    public FeedbackCommentService feedbackCommentServiceSeason2(FeedbackCommentListContainer container, BetaTestSeason2Date date) {
        return new FeedbackCommentService(container, date);
    }

    @Qualifier("feedbackCommentServiceBetaTestSeason3")
    @Bean
    public FeedbackCommentService feedbackCommentServiceSeason3(FeedbackCommentListContainer container, BetaTestSeason3Date date) {
        return new FeedbackCommentService(container, date);
    }
}
