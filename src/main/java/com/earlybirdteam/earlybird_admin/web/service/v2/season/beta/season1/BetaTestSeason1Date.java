package com.earlybirdteam.earlybird_admin.web.service.v2.season.beta.season1;

import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class BetaTestSeason1Date extends SeasonDate {
    private final LocalDate startDate = LocalDate.of(2024, 11, 1); // day0
    private final LocalDate endDate = LocalDate.of(2024, 11, 7);
}
