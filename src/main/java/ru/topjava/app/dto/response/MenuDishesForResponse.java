package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.topjava.app.entity.MenuDishes;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MenuDishesForResponse {
    private MenuDishes.Id id;
    private String dish;
    private String restaurant;
    private Date updatedAt;

    public MenuDishesForResponse(MenuDishes menuDishes) {
        this.id = menuDishes.getId();
        this.dish = String.valueOf(menuDishes.getDish().getName());
        this.restaurant = String.valueOf(menuDishes.getRestaurant().getName());
        this.updatedAt = menuDishes.getUpdatedAt();
    }
}
