package com.earlybirdteam.earlybird_admin.domain.notification;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FcmNotificationRepository extends JpaRepository<FcmNotification, Long> {
}
