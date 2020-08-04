package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.app.dto.response.MenuDishesForResponse;
import ru.topjava.app.service.MenuDishesService;

import java.util.List;

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
}
