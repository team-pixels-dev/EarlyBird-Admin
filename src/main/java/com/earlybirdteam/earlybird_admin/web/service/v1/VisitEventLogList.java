package com.earlybirdteam.earlybird_admin.web.service.v1;

import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLog;
import com.earlybirdteam.earlybird_admin.domain.log.visit.VisitEventLogRepository;
import com.earlybirdteam.earlybird_admin.web.service.DomainListContainer;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VisitEventLogList implements DomainListContainer {

    private final VisitEventLogRepository visitEventLogRepository;
    @Getter
    private List<VisitEventLog> visitEventLogs = new ArrayList<>();
    private LocalDateTime lastUpdateAt;
    private List<String> day0ClientId;
    @Getter
    private List<String> allClientIdList;

    private final Map<Integer, LocalDateTime> dayToDate = new HashMap<>();

    private List<Map<String, Boolean>> dayVisitByClientIdList;

    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @PostConstruct
    private void init() {
        visitEventLogs = visitEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        day0ClientId = visitEventLogs.stream()
                .filter(v -> v.getCreatedAt().isBefore(LocalDateTime.of(2024, 11, 2, 0, 0, 0)))
                .map(VisitEventLog::getClientId)
                .distinct()
                .toList();

        dayToDate.put(0, LocalDateTime.of(2024, 11, 1, 0, 0, 0));
        dayToDate.put(1, LocalDateTime.of(2024, 11, 2, 0, 0, 0));
        dayToDate.put(2, LocalDateTime.of(2024, 11, 3, 0, 0, 0));
        dayToDate.put(3, LocalDateTime.of(2024, 11, 4, 0, 0, 0));
        dayToDate.put(4, LocalDateTime.of(2024, 11, 5, 0, 0, 0));
        dayToDate.put(5, LocalDateTime.of(2024, 11, 6, 0, 0, 0));
        dayToDate.put(6, LocalDateTime.of(2024, 11, 7, 0, 0, 0));

        initDayVisitByClientIdList();
        initAllClientIdList();
    }

    private void initAllClientIdList() {
        allClientIdList = visitEventLogs.stream()
                .map(VisitEventLog::getClientId)
                .distinct()
                .toList();
    }

    private void initDayVisitByClientIdList() {
        dayVisitByClientIdList = Arrays.asList(
                new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
        visitEventLogs
                .forEach(v -> {
                    LocalDateTime createdAt = v.getCreatedAt();
                    LocalDateTime day0 = LocalDateTime.of(2024, 11, 1, 0, 0, 0);

                    int day = (int) ChronoUnit.DAYS.between(day0, createdAt);
                    if (day >= 0 && day < 7)
                        dayVisitByClientIdList.get(day).put(v.getClientId(), true);
                });
    }

    public Long getTotalVisitCountByClientId(String clientId) {
        return visitEventLogs.stream()
                .filter(v -> v.getClientId().equals(clientId))
                .map(v -> v.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .distinct()
                .count();
    }

    public List<Boolean> getDayVisitBoolByClientId(String clientId) {
        return Arrays.asList(
                dayVisitByClientIdList.get(0).get(clientId) != null,
                dayVisitByClientIdList.get(1).get(clientId) != null,
                dayVisitByClientIdList.get(2).get(clientId) != null,
                dayVisitByClientIdList.get(3).get(clientId) != null,
                dayVisitByClientIdList.get(4).get(clientId) != null,
                dayVisitByClientIdList.get(5).get(clientId) != null
        );
    }

    public Long getTotalVisitUserCount() {
        return (long) allClientIdList.size();
    }

    public Long day1UserCount() {
        return userCountBefore(LocalDateTime.of(2024, 11, 2, 0, 0));
    }

    public Long day2UserCount() {
        return userCountBefore(LocalDateTime.of(2024, 11, 3, 0, 0));
    }

    public Long userCountBefore(LocalDateTime before) {
        return visitEventLogs.stream()
                .filter(v -> v.getCreatedAt().isBefore(before))
                .map(VisitEventLog::getClientId)
                .distinct()
                .count();
    }

    public Double dayRetention(int day) {

        long dayUserCount = visitEventLogs.stream()
                .filter(v -> v.getCreatedAt().isAfter(dayToDate.get(day)) && v.getCreatedAt().isBefore(dayToDate.get(day + 1)))
                .map(VisitEventLog::getClientId)
                .distinct()
                .filter(id -> day0ClientId.contains(id))
                .count();
        return ((double) Math.round((double) dayUserCount / day0ClientId.size() * 100) / 100) * 100;
    }

    public void updateDataList() {
        visitEventLogs = visitEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        initDayVisitByClientIdList();
        initAllClientIdList();
    }
}
