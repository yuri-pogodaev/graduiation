package ru.topjava.app.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votes", schema = "PUBLIC")
public class Vote {
    @EmbeddedId
    private voteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class voteId implements Serializable {
        @Column(name = "user_id")
        private UUID userId;
        @Column(name = "restaurant_id")
        private UUID restaurantId;
    }

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date updatedAt;
}
