package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.VoteForInit;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.entity.Restaurant;
import ru.topjava.app.entity.User;
import ru.topjava.app.entity.Vote;
import ru.topjava.app.repository.RestaurantsRepository;
import ru.topjava.app.repository.UserRepository;
import ru.topjava.app.repository.VotesRepository;

import javax.imageio.stream.IIOByteBuffer;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private static final LocalTime VOTE_END_TIME = LocalTime.of(23, 0, 0);
    private final VotesRepository votesRepository;
    private final RestaurantsRepository restaurantsRepository;
    private final UserRepository userRepository;

    public VoteService(VotesRepository votesRepository, RestaurantsRepository restaurantsRepository, UserRepository userRepository) {
        this.votesRepository = votesRepository;
        this.restaurantsRepository = restaurantsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<VoteForResponse> getAll() {
        List<Vote> list = votesRepository.findAll();
        return list.stream().map(VoteForResponse::new).collect(Collectors.toList());
    }


    @Transactional
    public Vote createNewVote(@Valid VoteForInit voteForInit) throws Exception {
        User user = userRepository.findById(voteForInit.getUser())
                .orElseThrow(() -> new Exception(""));
        List<Vote> votes = votesRepository.findVoteByUserId(user.getId())
                .orElseThrow(() -> new Exception(""));

        Vote voteForThisDay = votes.stream().
                filter(x -> LocalDateTime.now().toLocalDate().equals(x.getUpdatedAt().toLocalDate()))
                .findAny().orElse(null);
        if (voteForThisDay != null && LocalDateTime.now()
                .toLocalTime().isBefore(VOTE_END_TIME)) {
            votesRepository.delete(voteForThisDay);
            Restaurant restaurant = restaurantsRepository.findById(voteForInit.getRestaurant())
                    .orElseThrow(() -> new Exception(""));
            Vote vote = Vote.builder()
                    .user(user)
                    .restaurant(restaurant)
                    .updatedAt(LocalDateTime.now())
                    .build();
            votesRepository.saveAndFlush(vote);
            return vote;
        } else if (voteForThisDay == null) {

            Restaurant restaurant = restaurantsRepository.findById(voteForInit.getRestaurant())
                    .orElseThrow(() -> new Exception(""));
            Vote vote = Vote.builder()
                    .user(user)
                    .restaurant(restaurant)
                    .updatedAt(LocalDateTime.now())
                    .build();
            votesRepository.saveAndFlush(vote);
            return vote;
        } else {
            throw new Exception("Vote time: " + VOTE_END_TIME + " is over.");
        }

    }

    @Transactional
    public VoteForResponse getById(UUID id) throws Exception {
        Vote vote = votesRepository.findById(id).orElseThrow(()-> new Exception("vote not found"));
        return new VoteForResponse(vote);
    }

    @Transactional
    public List<VoteForResponse> getHistory(UUID userId) {
        List<Vote> result = null;
        try {
            result = votesRepository.findVoteByUserId(userId)
                    .orElseThrow(() -> new Exception("Not Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert result != null;
        return result.stream().map(VoteForResponse::new).collect(Collectors.toList());
    }
}
