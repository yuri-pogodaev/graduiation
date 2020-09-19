package ru.topjava.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.app.service.RestaurantService;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/restaurants";
    private final RestaurantService restaurantService;

    public UserRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
//todo need get restaurants with menu for this day
}
