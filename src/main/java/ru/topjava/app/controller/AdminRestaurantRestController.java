package ru.topjava.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.app.dto.insert.MenuItemForInit;
import ru.topjava.app.dto.insert.RestaurantForInit;
import ru.topjava.app.dto.response.DishForResponse;
import ru.topjava.app.dto.response.MenuItemForResponse;
import ru.topjava.app.dto.response.RestaurantForResponse;
import ru.topjava.app.dto.update.MenuItemForUpdate;
import ru.topjava.app.dto.update.RestaurantForUpdate;
import ru.topjava.app.entity.MenuItem;
import ru.topjava.app.service.DishService;
import ru.topjava.app.service.MenuItemService;
import ru.topjava.app.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.topjava.app.controller.AdminRestaurantRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    public static final String MENUS_REST_URL = "/{id}/menus";
    public static final String REST_URL = "/admin/restaurants";

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final DishService dishService;

    public AdminRestaurantRestController(RestaurantService restaurantService, MenuItemService menuItemService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
        this.dishService = dishService;
    }

    //после успешного создания ресурса возвращают 201 + url созданного ресурса
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UUID> create(@RequestBody @Valid RestaurantForInit restaurantForInit) {
        log.info("create {}", restaurantForInit);
        UUID created = restaurantService.init(restaurantForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid RestaurantForUpdate restaurantForUpdate) {
        log.info("update {}", id);
        restaurantService.update(restaurantForUpdate, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws Exception {
        log.info("delete {}", id);
        restaurantService.deleteById(id);
    }

    @GetMapping("/{id}")
    public RestaurantForResponse getById(@PathVariable("id") UUID id) throws Exception {
        log.info("get {}", id);
        return restaurantService.getById(id);
    }

    @GetMapping
    public List<RestaurantForResponse> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    @PostMapping(value = MENUS_REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> save(@RequestBody @Valid MenuItemForInit menuItemForInit) throws Exception {
        log.info("create menu {} for restaurant {}", menuItemForInit.getDish(), menuItemForInit.getRestaurant());
        MenuItem created = menuItemService.createNewMenuDishes(menuItemForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + MENUS_REST_URL + "/{menuId}")
                .buildAndExpand(menuItemForInit.getId(), created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created.getId());
    }

    @PutMapping(value = MENUS_REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid MenuItemForUpdate menuItemForUpdate) throws Exception {
        log.info("update menu {} for restaurant {}", menuItemForUpdate.getDish(), menuItemForUpdate.getRestaurant());
        menuItemService.update(menuItemForUpdate, id);
    }

    @DeleteMapping(value = MENUS_REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable("id") UUID id) throws Exception {
        log.info("delete menu {} ", id);
        menuItemService.deleteById(id);
    }

    @GetMapping(value = MENUS_REST_URL + "/{id}")
    public MenuItemForResponse getMenuById(@PathVariable("id") UUID id) throws Exception {
        log.info("get {}",id);
        return menuItemService.getById(id);
    }

    @GetMapping(value = MENUS_REST_URL)
    public List<MenuItemForResponse> getAllMenu() {
        log.info("getAll");
        return menuItemService.getAll();
    }
}


