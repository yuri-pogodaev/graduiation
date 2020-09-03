package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.MenuDishesForInit;
import ru.topjava.app.dto.response.MenuDishesForResponse;
import ru.topjava.app.dto.update.MenuDishesForUpdate;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.MenuDishes;
import ru.topjava.app.entity.Restaurant;
import ru.topjava.app.repository.DishesRepository;
import ru.topjava.app.repository.MenuDishesRepository;
import ru.topjava.app.repository.RestaurantsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuDishesService {
    private final MenuDishesRepository menuDishesRepository;

    private final DishesRepository dishesRepository;
    private final RestaurantsRepository restaurantsRepository;

    public MenuDishesService(MenuDishesRepository menuDishesRepository, DishesRepository dishesRepository, RestaurantsRepository restaurantsRepository) {
        this.menuDishesRepository = menuDishesRepository;
        this.dishesRepository = dishesRepository;
        this.restaurantsRepository = restaurantsRepository;
    }

    @Transactional
    public List<MenuDishesForResponse> getAll() {
        List<MenuDishes> list = menuDishesRepository.findAll();
        return list.stream().map(MenuDishesForResponse::new).collect(Collectors.toList());
    }

//    public List<MenuDishes> getAllByDate(LocalDate updateAt){
////        List<MenuDishes> list = menuDishesRepository.getAllByUpdatedAt(updateAt);
////        return list.stream().map(MenuDishesForResponse::new).collect(Collectors.toList());
//        return menuDishesRepository.findAllByUpdatedAt(updateAt);
//    }

    public MenuDishesForResponse getById(UUID id) throws Exception {
        MenuDishes menuDishes = menuDishesRepository.findById(id).orElseThrow(() -> new Exception(""));
        return new MenuDishesForResponse(menuDishes);
    }

    @Transactional
    public void deleteById(UUID id) throws Exception {
        MenuDishes menuDishes = menuDishesRepository.findById(id).orElseThrow(() -> new Exception("dish not found"));
        menuDishesRepository.deleteById(menuDishes.getId());
    }

    @Transactional
    public MenuDishes createNewMenuDishes(MenuDishesForInit menuDishesForInit) throws Exception {
        Restaurant restaurant = restaurantsRepository.findById(menuDishesForInit.getRestaurant())
                .orElseThrow(() -> new Exception(""));
        Dish dish = dishesRepository.findById(menuDishesForInit.getDish())
                .orElseThrow(() -> new Exception(""));
        MenuDishes menuDishes = MenuDishes.builder()
                .restaurant(restaurant)
                .dish(dish)
                .updatedAt(LocalDate.now())
                .build();
        menuDishesRepository.saveAndFlush(menuDishes);
        return menuDishes;
    }

    @Transactional
    public MenuDishes update(MenuDishesForUpdate menuDishesForUpdate, UUID id) throws Exception {
        MenuDishes menuDishes = menuDishesRepository.findById(id).orElseThrow(() -> new Exception(""));
        Dish dish = dishesRepository.getOne(menuDishesForUpdate.getDish());
        menuDishes.setDish(dish);
        return menuDishesRepository.saveAndFlush(menuDishes);
        //        dishesRepository.findById(id)
//        menuDishesRepository.findById(id).map(menuDishes -> {
//            menuDishes.getDish().setId(menuDishesForUpdate.getDish());
//            return menuDishesRepository.saveAndFlush(menuDishes);
    }
}



