package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.topjava.app.entity.Vote;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class VoteForResponse {
    private Vote.VoteId id;
    private String user;
    private String restaurant;
    private Date updatedAt;

    public VoteForResponse(Vote vote) {
//        this.id = vote.getId();
        this.user = vote.getUser().getName();
        this.restaurant = vote.getRestaurant().getName();
        this.updatedAt = vote.getUpdatedAt();
    }
}
