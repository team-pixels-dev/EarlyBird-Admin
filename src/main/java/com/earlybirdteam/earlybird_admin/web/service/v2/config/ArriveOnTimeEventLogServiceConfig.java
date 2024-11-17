package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.ArriveOnTimeEventLogService;
import com.earlybirdteam.earlybird_admin.web.service.v2.UserCountService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.ArriveOnTimeEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArriveOnTimeEventLogServiceConfig {

    @Qualifier("arriveOnTimeEventLogServiceAllTime")
    @Bean
    public ArriveOnTimeEventLogService arriveOnTimeEventLogServiceAllTime(
            ArriveOnTimeEventLogListContainer arriveLogContainer,
            @Qualifier("userCountServiceAllTime") UserCountService userCountService,
            AppointmentListContainer appointmentContainer,
            AllTimeDate allTimeDate
    ) {
        return new ArriveOnTimeEventLogService(arriveLogContainer, userCountService, appointmentContainer, allTimeDate);
    }

    @Qualifier("arriveOnTimeEventLogServiceBetaTestSeason1")
    @Bean
    public ArriveOnTimeEventLogService arriveOnTimeEventLogServiceBetaTestSeason1(
            ArriveOnTimeEventLogListContainer arriveLogContainer,
            @Qualifier("userCountServiceBetaTestSeason1") UserCountService userCountService,
            AppointmentListContainer appointmentContainer,
            BetaTestSeason1Date betaTestSeason1Date
    ) {
        return new ArriveOnTimeEventLogService(arriveLogContainer, userCountService, appointmentContainer, betaTestSeason1Date);
    }

    @Qualifier("arriveOnTimeEventLogServiceBetaTestSeason2")
    @Bean
    public ArriveOnTimeEventLogService arriveOnTimeEventLogServiceBetaTestSeason2(
            ArriveOnTimeEventLogListContainer arriveLogContainer,
            @Qualifier("userCountServiceBetaTestSeason2") UserCountService userCountService,
            AppointmentListContainer appointmentContainer,
            BetaTestSeason2Date betaTestSeason2Date
    ) {
        return new ArriveOnTimeEventLogService(arriveLogContainer, userCountService, appointmentContainer, betaTestSeason2Date);
    }
}
