package com.earlybirdteam.earlybird_admin.web.service.v2.season.all;

import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Component
public class AllTimeDate extends SeasonDate {

    private final LocalDate startDate = LocalDate.of(2024,11,1);
    private final LocalDate endDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
}
