package com.earlybirdteam.earlybird_admin.web.service.v2;

import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLog;
import com.earlybirdteam.earlybird_admin.web.service.v2.container.VisitEventLogListContainer;
import com.earlybirdteam.earlybird_admin.web.service.v2.season.SeasonDate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserCountService {

    private final VisitEventLogListContainer visitEventLogListContainer;
    private final SeasonDate season;

    public List<Long> getUserCountsInSeason() {
        List<Long> resultList = new ArrayList<>();

        LocalDate userCountDate = season.getStartDate();
        while (!userCountDate.isAfter(season.getEndDate())) {
            resultList.add(getUserCount(userCountDate));
            userCountDate = userCountDate.plusDays(1);
        }

        return resultList;
    }

    private Long getUserCount(LocalDate targetDate) {
        if (!targetDateIsInSeason(targetDate))
            throw new IllegalArgumentException("날짜가 시즌 기간에 속하지 않습니다.");

        return visitEventLogListContainer
                .getVisitEventLogList().stream()
                .filter(v -> v.getCreatedAt().toLocalDate().equals(targetDate))
                .map(VisitEventLog::getClientId)
                .distinct()
                .count();
    }

    private boolean targetDateIsInSeason(LocalDate targetDate) {
        return !(targetDate.isBefore(season.getStartDate()) || targetDate.isAfter(season.getEndDate()));
    }

    public Long getTotalUserCountInSeason() {
        return visitEventLogListContainer.getVisitEventLogList().stream()
                .filter(v -> {
                    LocalDate createdDate = v.getCreatedAt().toLocalDate();
                    return !(createdDate.isBefore(season.getStartDate()) || createdDate.isAfter(season.getEndDate()));
                })
                .map(VisitEventLog::getClientId)
                .distinct()
                .count();
    }
}
