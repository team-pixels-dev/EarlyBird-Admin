package com.earlybirdteam.earlybird_admin.web.service.v2.container;

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
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VisitEventLogListContainer implements DomainListContainer {

    private final VisitEventLogRepository visitEventLogRepository;
    @Getter
    private List<VisitEventLog> visitEventLogList = new ArrayList<>();
    private LocalDateTime lastUpdateAt;

    @PostConstruct
    private void init() {
        updateDataList();
    }

    @Override
    public String getLastUpdateAt() {
        return lastUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void updateDataList() {
        visitEventLogList = visitEventLogRepository.findAll();
        lastUpdateAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
