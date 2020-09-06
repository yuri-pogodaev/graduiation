package ru.topjava.app.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Vote;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VoteForUpdate {

    private String user;
    private String restaurant;
    private LocalDateTime updatedAt;

    public VoteForUpdate(Vote vote) {
        this.user = vote.getUser().getName();
        this.restaurant = vote.getRestaurant().getName();
        this.updatedAt = vote.getUpdatedAt();
    }

}