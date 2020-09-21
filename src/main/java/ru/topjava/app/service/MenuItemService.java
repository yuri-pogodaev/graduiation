package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.MenuItemForInit;
import ru.topjava.app.dto.response.MenuForDay;
import ru.topjava.app.dto.response.MenuItemForResponse;
import ru.topjava.app.dto.update.MenuItemForUpdate;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.MenuItem;
import ru.topjava.app.entity.Restaurant;
import ru.topjava.app.repository.DishesRepository;
import ru.topjava.app.repository.MenuItemRepository;
import ru.topjava.app.repository.RestaurantsRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    private final DishesRepository dishesRepository;
    private final RestaurantsRepository restaurantsRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, DishesRepository dishesRepository, RestaurantsRepository restaurantsRepository) {
        this.menuItemRepository = menuItemRepository;
        this.dishesRepository = dishesRepository;
        this.restaurantsRepository = restaurantsRepository;
    }

    public List<MenuItemForResponse> getAll() {
        List<MenuItem> list = menuItemRepository.findAll();
        return list.stream().map(MenuItemForResponse::new).collect(Collectors.toList());
    }

//    public List<MenuDishes> getAllByDate(LocalDate updateAt){
////        List<MenuDishes> list = menuDishesRepository.getAllByUpdatedAt(updateAt);
////        return list.stream().map(MenuDishesForResponse::new).collect(Collectors.toList());
//        return menuDishesRepository.findAllByUpdatedAt(updateAt);
//    }

    public MenuItemForResponse getById(UUID id) throws Exception {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new Exception(""));
        return new MenuItemForResponse(menuItem);
    }


    public void deleteById(UUID id) throws Exception {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new Exception("dish not found"));
        menuItemRepository.deleteById(menuItem.getId());
    }

    public MenuItem createNewMenuDishes(MenuItemForInit menuItemForInit) throws Exception {
        Restaurant restaurant = restaurantsRepository.findById(menuItemForInit.getRestaurant())
                .orElseThrow(() -> new Exception(""));
        Dish dish = dishesRepository.findById(menuItemForInit.getDish())
                .orElseThrow(() -> new Exception(""));
        MenuItem menuItem = MenuItem.builder()
                .restaurant(restaurant)
                .dish(dish)
                .updatedAt(LocalDate.now())
                .build();
        menuItemRepository.saveAndFlush(menuItem);
        return menuItem;
    }


    public MenuItem update(MenuItemForUpdate menuItemForUpdate, UUID id) throws Exception {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new Exception(""));
        Dish dish = dishesRepository.getOne(menuItemForUpdate.getDish());
        menuItem.setDish(dish);
        return menuItemRepository.saveAndFlush(menuItem);
    }

    public List<MenuForDay> getAllForVoteInThisDay(LocalDate date) throws Exception {
        List<MenuForDay> response = new ArrayList<>();
        List<MenuItem> list = menuItemRepository.getAllByUpdatedAt(date);
        HashSet<Restaurant> rest = new HashSet<>();
        list.forEach(x-> rest.add(x.getRestaurant()));
        rest.forEach(x-> {
            MenuForDay menu = new MenuForDay();
            menu.setUpdatedAt(date);
            menu.setRestaurant(x.getName());
            List<String> dishes = new ArrayList<>();
            list.forEach(a-> {
                if (a.getRestaurant().equals(x)) {
                    dishes.add(a.getDish().getName());
                }
            });
            menu.setDish(dishes);
            response.add(menu);
        });

        return response;
    }
}



