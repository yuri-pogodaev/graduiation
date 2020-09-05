package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.MenuDishes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDishesForResponse {
    private UUID id;
    private String dish;
    private String restaurant;
    private LocalDate updatedAt;

    public MenuDishesForResponse(MenuDishes menuDishes) {
        this.id = menuDishes.getId();
        this.dish = String.valueOf(menuDishes.getDish().getName());
        this.restaurant = String.valueOf(menuDishes.getRestaurant().getName());
        this.updatedAt = menuDishes.getUpdatedAt();
    }
}
