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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService {
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
    public void update(@Valid VoteForUpdate voteForUpdate, UUID id) throws Exception {
        Optional<Vote> vote = votesRepository.findVoteByUserId(id);
        if (vote.isPresent()) {
//            votesRepository.delete(id);
            votesRepository.flush();
        }
        Vote newVote = new Vote();
        newVote.setUpdatedAt(voteForUpdate.getUpdatedAt());
        Restaurant restaurant = restaurantsRepository.
                findById(UUID.fromString(voteForUpdate.getRestaurant())).orElseThrow(() -> new Exception(""));
        newVote.setRestaurant(restaurant);
        User user = userRepository.
                findById(id).orElseThrow(() -> new Exception(""));
        newVote.setUser(user);
        Vote.VoteId voteId = Vote.VoteId.builder()
                .restaurantId(restaurant.getId())
                .userId(user.getId())
                .build();
        newVote.setId(voteId);
        votesRepository.saveAndFlush(newVote);
        //TODO добавить логику голосования
    }

    @Transactional
    public void create(@Valid VoteForInit voteForInit, UUID id) throws Exception {

    }

    public VoteForResponse getById(UUID restaurantId, UUID userId) {
        Vote vote = votesRepository.findByRestaurantIdAndUserId(restaurantId, userId);
        return new VoteForResponse(vote);
    }


}
