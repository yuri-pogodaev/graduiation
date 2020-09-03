package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.VoteForInit;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.dto.update.VoteForUpdate;
import ru.topjava.app.entity.Restaurant;
import ru.topjava.app.entity.User;
import ru.topjava.app.entity.Vote;
import ru.topjava.app.repository.RestaurantsRepository;
import ru.topjava.app.repository.UserRepository;
import ru.topjava.app.repository.VotesRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private static final LocalTime VOTE_END_TIME = LocalTime.of(17, 0, 0);
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
        if (voteForThisDay != null &&  LocalDateTime.now()
                .toLocalTime().isBefore(VOTE_END_TIME)){
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
        }
        else {
            throw new Exception("Vote time: " + VOTE_END_TIME + " is over.");
        }

//        Objects.requireNonNull(voteForThisDay).getUpdatedAt().toLocalTime().
//        LocalDateTime.now().toLocalTime();
//        Restaurant restaurant = restaurantsRepository.findById(voteForInit.getRestaurant())
//                .orElseThrow(() -> new Exception(""));
//        Vote vote = Vote.builder()
//                .user(user)
//                .restaurant(restaurant)
//                .updatedAt(LocalDateTime.now())
//                .build();
//        votesRepository.saveAndFlush(vote);

//        return vote;

    }

    public VoteForResponse getById(UUID restaurantId, UUID userId) {
        Vote vote = votesRepository.findByRestaurantIdAndUserId(restaurantId, userId);
        return new VoteForResponse(vote);
    }


}
