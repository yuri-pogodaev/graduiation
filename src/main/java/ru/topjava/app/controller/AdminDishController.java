package ru.topjava.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.app.dto.insert.DishForInit;
import ru.topjava.app.dto.response.DishForResponse;
import ru.topjava.app.dto.update.DishForUpdate;
import ru.topjava.app.service.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.topjava.app.controller.AdminDishController.REST_DISH_URL;

@RestController
@RequestMapping(value = REST_DISH_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {
    private final DishService dishService;
    public static final String REST_DISH_URL = "/admin/dish";
    protected final Logger log = LoggerFactory.getLogger(getClass());


    public AdminDishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<DishForResponse> getAll() {
        log.info("get all");
        return dishService.getAll();
    }

    @GetMapping("/{id}")
    public DishForResponse getById(@PathVariable("id") UUID id) throws Exception {
        log.info("get {}",id);
        return dishService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> save(@RequestBody @Valid DishForInit dishForInit) {
        log.info("create new dish {}", dishForInit.getId());
        UUID created = dishService.init(dishForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_DISH_URL + "/{dishId}")
                .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws Exception {
       log.info("delete {}", id);
        dishService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid DishForUpdate dishForUpdate) throws Exception {
        log.info("update dish{}",id);
        dishService.update(dishForUpdate, id);
    }
}
