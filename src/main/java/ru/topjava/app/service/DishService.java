package ru.topjava.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.app.dto.insert.DishForInit;
import ru.topjava.app.dto.response.DishForResponse;
import ru.topjava.app.dto.update.DishForUpdate;
import ru.topjava.app.dto.update.UserForUpdate;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.repository.DishesRepository;
import ru.topjava.app.repository.MenuDishesRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishesRepository dishesRepository;
    private final MenuDishesRepository menuDishesRepository;

    public DishService(DishesRepository dishesRepository, MenuDishesRepository menuDishesRepository) {
        this.dishesRepository = dishesRepository;
        this.menuDishesRepository = menuDishesRepository;
    }

    @Transactional
    public List<DishForResponse> getAll() {
        List<Dish> list = dishesRepository.findAll();
        return list.stream().map(DishForResponse::new).collect(Collectors.toList());
    }

    public DishForResponse getById(UUID id) throws Exception {
        Dish dish = dishesRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        return new DishForResponse(dish);
    }

    public Dish createNewDish(@Valid DishForInit dishForInit) {
        Dish newDish = Dish.builder()
                .id(dishForInit.getId())
                .name(dishForInit.getName())
                .price(dishForInit.getPrice())
                .build();
        dishesRepository.saveAndFlush(newDish);
        return newDish;
    }

    @Transactional
    public UUID init(@Valid DishForInit dishForInit) {
        Dish newDish = createNewDish(dishForInit);
        return newDish.getId();
    }

    @Transactional
    public void deleteById(UUID id) throws Exception {
        Dish dish = dishesRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
       menuDishesRepository.delete(dish.getId());
        dishesRepository.deleteById(dish.getId());

    }

    @Transactional
    public void update(DishForUpdate dishForUpdate, UUID id) {
        dishesRepository.findById(id).map(user -> {
            user.setName(dishForUpdate.getName());
            user.setPrice(dishForUpdate.getPrice());
            return dishesRepository.saveAndFlush(user);
        });

    }
}
