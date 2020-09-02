package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.UserForInit;
import ru.topjava.app.dto.response.UserForResponse;
import ru.topjava.app.dto.update.UserForUpdate;
import ru.topjava.app.entity.User;
import ru.topjava.app.repository.UserRepository;
import ru.topjava.app.repository.VotesRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final VotesRepository votesRepository;

    public UserService(UserRepository userRepository, VotesRepository votesRepository) {
        this.userRepository = userRepository;
        this.votesRepository = votesRepository;
    }

    @Transactional
    public List<UserForResponse> getAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(UserForResponse::new).collect(Collectors.toList());
    }

    public UserForResponse getById(UUID id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        return new UserForResponse(user);
    }


    public User createNewUser(@Valid UserForInit userForInit) {
        User newUser = User.builder()
                .id(userForInit.getId())
                .email(userForInit.getEmail())
                .name(userForInit.getName())
                .password(userForInit.getPassword())
                .registered(userForInit.getRegistered())
                .role(userForInit.getRole())
                .build();
        userRepository.saveAndFlush(newUser);
        return newUser;
    }

    @Transactional
    public UUID init(@Valid UserForInit userForInit) {
        User newUser = createNewUser(userForInit);

        return newUser.getId();
    }

    @Transactional
    public void update(@Valid UserForUpdate userForUpdate, UUID id) {
        userRepository.findById(id).map(user -> {
            user.setName(userForUpdate.getName());
            user.setEmail(userForUpdate.getEmail());
            user.setPassword(userForUpdate.getPassword());
            return userRepository.saveAndFlush(user);
        });

    }

    @Transactional
    public void deleteById(UUID id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        votesRepository.delete(user.getId());
        userRepository.deleteById(user.getId());

    }

}
