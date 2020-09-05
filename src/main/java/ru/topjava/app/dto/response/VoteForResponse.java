package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteForResponse {
    private UUID id;
    private String user;
    private String restaurant;
    private LocalDateTime updatedAt;

    public VoteForResponse(Vote vote) {
        this.id = vote.getId();
        this.user = vote.getUser().getName();
        this.restaurant = vote.getRestaurant().getName();
        this.updatedAt = vote.getUpdatedAt();
    }
}
