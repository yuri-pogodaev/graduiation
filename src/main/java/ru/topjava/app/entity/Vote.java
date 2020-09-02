package ru.topjava.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "votes", schema = "PUBLIC")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;

//    @Data
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Embeddable
//    public static class VoteId implements Serializable {
//        @Column(name = "user_id")
//        private UUID userId;
//        @Column(name = "restaurant_id")
//        private UUID restaurantId;
//    }

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date updatedAt;

    public Vote(Date updatedAt, User user, Restaurant restaurant) {
        this.updatedAt = updatedAt;
        this.user = user;
        this.restaurant = restaurant;
    }
}
