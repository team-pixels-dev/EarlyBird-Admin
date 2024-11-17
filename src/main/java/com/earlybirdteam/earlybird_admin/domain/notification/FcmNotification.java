package com.earlybirdteam.earlybird_admin.domain.notification;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.earlybirdteam.earlybird_admin.domain.notification.NotificationStatus.PENDING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FcmNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcm_notification_id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStep notificationStep;

    @Column(nullable = false)
    private LocalDateTime targetTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status = PENDING;

    private LocalDateTime sentTime;

    @Builder
    private FcmNotification(Appointment appointment, NotificationStep notificationStep, LocalDateTime targetTime) {
        this.appointment = appointment;
        this.notificationStep = notificationStep;
        this.targetTime = targetTime;
    }
}

