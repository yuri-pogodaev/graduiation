package ru.topjava.app.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.MenuDishes;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuDishesForUpdate {
//    private UUID id;
        private UUID dish;
//    private UUID restaurant;

        public MenuDishesForUpdate(MenuDishes menuDishes) {
//                this.m = dish.getDish();
                this.dish = menuDishes.getId();
        }
}
