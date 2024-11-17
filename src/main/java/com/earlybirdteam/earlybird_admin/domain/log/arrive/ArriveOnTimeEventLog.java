package com.earlybirdteam.earlybird_admin.domain.log.arrive;

import com.earlybirdteam.earlybird_admin.domain.appointment.Appointment;
import com.earlybirdteam.earlybird_admin.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "arrive_on_time_event_log")
@Entity
public class ArriveOnTimeEventLog extends BaseEntity {

    @Column(name = "arrive_on_time_event_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public ArriveOnTimeEventLog() {}

    public ArriveOnTimeEventLog(Appointment appointment) {
        this.appointment = appointment;
    }
}
