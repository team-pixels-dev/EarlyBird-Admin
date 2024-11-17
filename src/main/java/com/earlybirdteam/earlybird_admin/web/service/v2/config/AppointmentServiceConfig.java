package com.earlybirdteam.earlybird_admin.web.service.v2.config;

import com.earlybirdteam.earlybird_admin.web.service.v2.AppointmentService;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.AppointmentListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.all.AllTimeDate;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1.BetaTestSeason1Date;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2.BetaTestSeason2Date;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentServiceConfig {

    @Qualifier("appointmentServiceAllTime")
    @Bean
    public AppointmentService appointmentServiceAllTime(AppointmentListContainer appointmentListContainer, AllTimeDate allTimeDate) {
        return new AppointmentService(appointmentListContainer, allTimeDate);
    }

    @Qualifier("appointmentServiceBetaTestSeason1")
    @Bean
    public AppointmentService appointmentServiceSeason1(AppointmentListContainer appointmentListContainer, BetaTestSeason1Date betaTestSeason1Date) {
        return new AppointmentService(appointmentListContainer, betaTestSeason1Date);
    }

    @Qualifier("appointmentServiceBetaTestSeason2")
    @Bean
    public AppointmentService appointmentServiceSeason2(AppointmentListContainer appointmentListContainer, BetaTestSeason2Date betaTestSeason2Date) {
        return new AppointmentService(appointmentListContainer, betaTestSeason2Date);
    }
}
