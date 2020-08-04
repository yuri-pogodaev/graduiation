package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.entity.Vote;
import ru.topjava.app.repository.RestaurantsRepository;
import ru.topjava.app.repository.UserRepository;
import ru.topjava.app.repository.VotesRepository;

import java.util.List;
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

//    @Transactional
//    public void update(@Valid VoteForUpdate voteForUpdate, Vote.voteId voteId) throws Exception {
//
//        votesRepository.findById(new Vote.voteId()).map(vote -> {
//            vote.getRestaurant().setName(voteForUpdate.getRestaurant());
//            vote.getUser().setName(voteForUpdate.getUser());
//            vote.setUpdatedAt(voteForUpdate.getUpdatedAt());
//            return votesRepository.saveAndFlush(vote);
//        });
//
//    }
    public VoteForResponse getById(UUID restaurantId, UUID userId) {
        Vote vote =votesRepository.findByRestaurantIdAndUserId(restaurantId,userId);
        return new VoteForResponse(vote);
    }
}
