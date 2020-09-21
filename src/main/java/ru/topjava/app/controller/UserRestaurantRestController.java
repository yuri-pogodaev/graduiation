package ru.topjava.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.app.dto.response.MenuForDay;
import ru.topjava.app.entity.MenuItem;
import ru.topjava.app.service.MenuItemService;
import ru.topjava.app.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/restaurants";
    private final MenuItemService menuItemService;

    public UserRestaurantRestController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public List<MenuForDay> getAll() throws Exception {
        log.info("getAll");
        return menuItemService.getAllForVoteInThisDay(LocalDate.of(2020,7,6));
//        return menuItemService.getAllForVoteInThisDay(LocalDate.now());
    }
}
