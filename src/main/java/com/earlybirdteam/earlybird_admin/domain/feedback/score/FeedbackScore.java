package com.earlybirdteam.earlybird_admin.domain.feedback.score;

import com.earlybirdteam.earlybird_admin.domain.User.User;
import com.earlybirdteam.earlybird_admin.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class FeedbackScore extends BaseEntity {

    @Column(name = "feedback_score_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "feedback_nps_score", nullable = false)
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String clientId;

    @Column(name = "feedback_created_time_at_client", nullable = false)
    private LocalDateTime createdTimeAtClient;
}
