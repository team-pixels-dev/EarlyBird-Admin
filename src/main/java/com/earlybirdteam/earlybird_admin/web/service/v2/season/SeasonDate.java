package com.earlybirdteam.earlybird_admin.web.service.v2.season;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class SeasonDate {
    public abstract LocalDate getStartDate();
    public abstract LocalDate getEndDate();

    public boolean checkDateIsInSeason(LocalDateTime dateTime) {
        return checkDateIsInSeason(dateTime.toLocalDate());
    }

    public boolean checkDateIsInSeason(LocalDate date) {
        return !(date.isBefore(getStartDate()) || date.isAfter(getEndDate()));
    }
}
