package ru.topjava.app.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.MenuItem;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuItemForUpdate {
    private UUID dish;
private UUID restaurant;
    public MenuItemForUpdate(MenuItem menuItem) {
//                this.m = dish.getDish();
        this.dish = menuItem.getId();
    }
}
