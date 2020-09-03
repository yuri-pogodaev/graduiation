package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.insert.MenuDishesForInit;
import ru.topjava.app.dto.response.MenuDishesForResponse;
import ru.topjava.app.dto.update.MenuDishesForUpdate;
import ru.topjava.app.entity.MenuDishes;
import ru.topjava.app.service.MenuDishesService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/menuDishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuDishesController {
    private final MenuDishesService menuDishesService;

    public MenuDishesController(MenuDishesService menuDishesService) {
        this.menuDishesService = menuDishesService;
    }

    @GetMapping("/all")
    public List<MenuDishesForResponse> getAll() {
        return menuDishesService.getAll();
    }

//    @GetMapping(params = "date")
//    public List<MenuDishes> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//
//        return menuDishesService.getAllByDate(date);
//    }

    @GetMapping("/{id}")
    public MenuDishesForResponse getById(@PathVariable("id") UUID id) {
        try {
            return menuDishesService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) throws Exception {
        menuDishesService.deleteById(id);
    }

    @PostMapping("/save")
    public UUID save(@RequestBody @Valid MenuDishesForInit menuDishesForInit) throws Exception {
        MenuDishes created = menuDishesService.createNewMenuDishes(menuDishesForInit);
        return created.getId();
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid MenuDishesForUpdate menuDishesForUpdate) throws Exception {
        menuDishesService.update(menuDishesForUpdate, id);
    }
}
