package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.FeedbackScoreService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.FeedbackScoreListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season3.BetaTestSeason3Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackScoreServiceConfig {

    @Qualifier("feedbackScoreServiceAllTime")
    @Bean
    public FeedbackScoreService feedbackScoreServiceAllTime(FeedbackScoreListContainer container, AllTimeDate date) {
        return new FeedbackScoreService(container, date);
    }

    @Qualifier("feedbackScoreServiceBetaTestSeason1")
    @Bean
    public FeedbackScoreService feedbackScoreServiceBetaTestSeason1(FeedbackScoreListContainer container, BetaTestSeason1Date date) {
        return new FeedbackScoreService(container, date);
    }

    @Qualifier("feedbackScoreServiceBetaTestSeason2")
    @Bean
    public FeedbackScoreService feedbackScoreServiceBetaTestSeason2(FeedbackScoreListContainer container, BetaTestSeason2Date date) {
        return new FeedbackScoreService(container, date);
    }

    @Qualifier("feedbackScoreServiceBetaTestSeason3")
    @Bean
    public FeedbackScoreService feedbackScoreServiceBetaTestSeason3(FeedbackScoreListContainer container, BetaTestSeason3Date date) {
        return new FeedbackScoreService(container, date);
    }
}
