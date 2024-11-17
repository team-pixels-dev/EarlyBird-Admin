package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLog;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.VisitEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DayRetentionService {

    private final VisitEventLogListContainer visitEventLogListContainer;
    private final SeasonDate season;

    private List<String> day0ClientId;

    @PostConstruct
    private void init() {
        initDay0ClientId();
    }

    private void initDay0ClientId() {
        List<VisitEventLog> visitEventLogList = visitEventLogListContainer.getVisitEventLogList();
        day0ClientId = visitEventLogList.stream()
                .filter(v -> v.getCreatedAt().toLocalDate().equals(season.getStartDate()))
                .map(VisitEventLog::getClientId)
                .distinct()
                .toList();
    }

    private Double getDayRetention(LocalDate targetDate) {
        if (!targetDateIsInSeason(targetDate))
            throw new IllegalArgumentException("날짜가 시즌 기간에 속하지 않습니다.");

        double dayRetention = visitEventLogListContainer.getVisitEventLogList().stream()
                .filter(v -> v.getCreatedAt().toLocalDate().equals(targetDate))
                .map(VisitEventLog::getClientId)
                .distinct()
                .filter(id -> day0ClientId.contains(id))
                .count() / (double) day0ClientId.size();

        return (double) Math.round(dayRetention * 100) / 100 * 100;
    }

    public List<Double> getDayRetentionsInSeason() {
        List<Double> resultList = new ArrayList<>();

        LocalDate retentionDate = season.getStartDate();
        while (!retentionDate.isAfter(season.getEndDate())) {
            resultList.add(getDayRetention(retentionDate));
            retentionDate = retentionDate.plusDays(1);
        }

        return resultList;
    }

    private boolean targetDateIsInSeason(LocalDate targetDate) {
        return !(targetDate.isBefore(season.getStartDate()) || targetDate.isAfter(season.getEndDate()));
    }
}
