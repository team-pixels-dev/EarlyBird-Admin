package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.DayRetentionService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.VisitEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season3.BetaTestSeason3Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DayRetentionServiceConfig {

    @Qualifier("dayRetentionServiceAllTime")
    @Bean
    public DayRetentionService dayRetentionServiceAllTime(VisitEventLogListContainer container, AllTimeDate date) {
        return new DayRetentionService(container, date);
    }

    @Qualifier("dayRetentionServiceBetaTestSeason1")
    @Bean
    public DayRetentionService dayRetentionServiceBetaTestSeason1(VisitEventLogListContainer container, BetaTestSeason1Date date) {
        return new DayRetentionService(container, date);
    }

    @Qualifier("dayRetentionServiceBetaTestSeason2")
    @Bean
    public DayRetentionService dayRetentionServiceBetaTestSeason2(VisitEventLogListContainer container, BetaTestSeason2Date date) {
        return new DayRetentionService(container, date);
    }

    @Qualifier("dayRetentionServiceBetaTestSeason3")
    @Bean
    public DayRetentionService dayRetentionServiceBetaTestSeason3(VisitEventLogListContainer container, BetaTestSeason3Date date) {
        return new DayRetentionService(container, date);
    }
}
