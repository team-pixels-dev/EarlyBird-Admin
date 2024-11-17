package com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season2;

import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Component
public class BetaTestSeason2Date extends SeasonDate {
    private final LocalDate startDate = LocalDate.of(2024, 11, 13); // day0
    private final LocalDate endDate = LocalDate.of(2024, 11, 17);
}
