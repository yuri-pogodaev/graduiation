package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import ru.topjava.app.dto.insert.RestaurantForInit;
import ru.topjava.app.dto.response.RestaurantForResponse;
import ru.topjava.app.dto.update.RestaurantForUpdate;
import ru.topjava.app.entity.Restaurant;
import ru.topjava.app.repository.MenuItemRepository;
import ru.topjava.app.repository.RestaurantsRepository;
import ru.topjava.app.repository.VotesRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantsRepository restaurantsRepository;
    private final MenuItemRepository menuItemRepository;
    private final VotesRepository votesRepository;

    public RestaurantService(RestaurantsRepository restaurantsRepository, MenuItemRepository menuItemRepository, VotesRepository votesRepository) {
        this.restaurantsRepository = restaurantsRepository;
        this.menuItemRepository = menuItemRepository;
        this.votesRepository = votesRepository;
    }

    public List<RestaurantForResponse> getAll() {
        List<Restaurant> list = restaurantsRepository.findAll();
        return list.stream().map(RestaurantForResponse::new).collect(Collectors.toList());
    }

    public RestaurantForResponse getById(UUID id) throws Exception {
        Restaurant restaurant = restaurantsRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        return new RestaurantForResponse(restaurant);
    }

    public Restaurant createNewRestaurant(@Valid RestaurantForInit restaurantForInit) {
        Restaurant newRestaurant = Restaurant.builder()
                .id(restaurantForInit.getId())
                .name(restaurantForInit.getName())
                .address(restaurantForInit.getAddress())
                .build();
        restaurantsRepository.saveAndFlush(newRestaurant);
        return newRestaurant;
    }

    public UUID init(@Valid RestaurantForInit restaurantForInit) {
        Restaurant newRestaurant = createNewRestaurant(restaurantForInit);
        return newRestaurant.getId();
    }

    public void update(@Valid RestaurantForUpdate restaurantForUpdate, UUID id) {
        restaurantsRepository.findById(id).map(user -> {
            user.setName(restaurantForUpdate.getName());
            user.setAddress(restaurantForUpdate.getAddress());
            return restaurantsRepository.saveAndFlush(user);
        });

    }

    public void deleteById(UUID id) throws Exception {
        Restaurant restaurant = restaurantsRepository.findById(id).orElseThrow(() -> new Exception("restaurant not found"));
        restaurantsRepository.deleteById(restaurant.getId());
    }
}
