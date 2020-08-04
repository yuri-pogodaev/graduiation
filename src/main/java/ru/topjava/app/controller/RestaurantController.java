package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.insert.RestaurantForInit;
import ru.topjava.app.dto.response.RestaurantForResponse;
import ru.topjava.app.dto.update.RestaurantForUpdate;
import ru.topjava.app.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public List<RestaurantForResponse> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public RestaurantForResponse getById(@PathVariable("id") UUID id) {
        try {
            return restaurantService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/save")
    public UUID save(@RequestBody @Valid RestaurantForInit restaurantForInit) {
        return restaurantService.init(restaurantForInit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) throws Exception {
        restaurantService.deleteById(id);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid RestaurantForUpdate restaurantForUpdate) throws Exception {
        restaurantService.update(restaurantForUpdate, id);
    }
}
