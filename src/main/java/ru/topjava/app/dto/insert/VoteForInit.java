package ru.topjava.app.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Vote;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VoteForInit {
    private Vote.VoteId id;
    private String user;
    private String restaurant;
    private Date updatedAt;
}
