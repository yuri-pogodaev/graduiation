package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.MenuItem;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuForDay {
    private String restaurant;
    private LocalDate updatedAt;
    private String dish;

    public MenuForDay(MenuItem menuItem) {
        this.restaurant = menuItem.getRestaurant().getName();
        this.updatedAt = menuItem.getUpdatedAt();
        this.dish = menuItem.getDish().getName();
    }
}
