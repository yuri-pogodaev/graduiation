package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.response.MenuDishesForResponse;
import ru.topjava.app.entity.MenuDishes;
import ru.topjava.app.repository.MenuDishesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuDishesService {
    private final MenuDishesRepository menuDishesRepository;

    public MenuDishesService(MenuDishesRepository menuDishesRepository) {
        this.menuDishesRepository = menuDishesRepository;
    }

    @Transactional
    public List<MenuDishesForResponse> getAll() {
        List<MenuDishes> list = menuDishesRepository.findAll();
        return list.stream().map(MenuDishesForResponse::new).collect(Collectors.toList());
    }

}
