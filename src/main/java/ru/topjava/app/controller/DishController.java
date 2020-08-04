package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.insert.DishForInit;
import ru.topjava.app.dto.response.DishForResponse;
import ru.topjava.app.dto.update.DishForUpdate;
import ru.topjava.app.service.DishService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/dish", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public List<DishForResponse> getAll() {
        return dishService.getAll();
    }

    @GetMapping("/{id}")
    public DishForResponse getById(@PathVariable("id") UUID id) {
        try {
            return dishService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/save")
    public UUID save(@RequestBody @Valid DishForInit dishForInit) {
        return dishService.init(dishForInit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) throws Exception {
        dishService.deleteById(id);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid DishForUpdate dishForUpdate) throws Exception {
        dishService.update(dishForUpdate, id);
    }
}
