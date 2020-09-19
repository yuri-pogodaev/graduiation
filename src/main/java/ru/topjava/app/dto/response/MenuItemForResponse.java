package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.MenuItem;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemForResponse {
    private UUID id;
    private String dish;
    private String restaurant;
    private LocalDate updatedAt;

    public MenuItemForResponse(MenuItem menuItem) {
        this.id = menuItem.getId();
        this.dish = String.valueOf(menuItem.getDish().getName());
        this.restaurant = String.valueOf(menuItem.getRestaurant().getName());
        this.updatedAt = menuItem.getUpdatedAt();
    }
}
