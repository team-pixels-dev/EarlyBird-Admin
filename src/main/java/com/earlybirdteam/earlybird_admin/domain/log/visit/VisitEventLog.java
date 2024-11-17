package com.earlybirdteam.earlybird_admin.domain.log.visit;

import com.earlybirdteam.earlybird_admin.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class VisitEventLog extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String clientId;

    public VisitEventLog() {}

    public VisitEventLog(String clientId) {
        this.clientId = clientId;
    }
}
