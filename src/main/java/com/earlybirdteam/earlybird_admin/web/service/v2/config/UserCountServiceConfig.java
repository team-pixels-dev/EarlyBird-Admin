package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.UserCountService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.VisitEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season3.BetaTestSeason3Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCountServiceConfig {

    @Qualifier("userCountServiceAllTime")
    @Bean
    public UserCountService userCountServiceAllTime(VisitEventLogListContainer container, AllTimeDate date) {
        return new UserCountService(container, date);
    }

    @Qualifier("userCountServiceBetaTestSeason1")
    @Bean
    public UserCountService userCountServiceSeason1(VisitEventLogListContainer container, BetaTestSeason1Date date) {
        return new UserCountService(container, date);
    }

    @Qualifier("userCountServiceBetaTestSeason2")
    @Bean
    public UserCountService userCountServiceSeason2(VisitEventLogListContainer container, BetaTestSeason2Date date) {
        return new UserCountService(container, date);
    }

    @Qualifier("userCountServiceBetaTestSeason3")
    @Bean
    public UserCountService userCountServiceSeason3(VisitEventLogListContainer container, BetaTestSeason3Date date) {
        return new UserCountService(container, date);
    }
}
