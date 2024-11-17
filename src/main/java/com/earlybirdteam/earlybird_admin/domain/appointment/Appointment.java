package com.earlybirdteam.earlybird_admin.domain.appointment;

import com.earlybirdteam.earlybird_admin.domain.base.BaseEntity;
import com.earlybirdteam.earlybird_admin.domain.notification.FcmNotification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(nullable = false)
    private String appointmentName;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String deviceToken;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    private List<FcmNotification> fcmNotifications = new ArrayList<>();

    public void addFcmNotification(FcmNotification fcmNotification) {
        this.fcmNotifications.add(fcmNotification);
        fcmNotification.setAppointment(this);
    }

    @Builder
    private Appointment(String appointmentName, String clientId, String deviceToken) {
        this.appointmentName = appointmentName;
        this.clientId = clientId;
        this.deviceToken = deviceToken;
    }
}
