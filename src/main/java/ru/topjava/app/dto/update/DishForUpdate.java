package ru.topjava.app.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Dish;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishForUpdate {
    @NotBlank
    @Size(min = 10, max = 100)
    private String name;
    @NotNull
    private BigDecimal price;

    public DishForUpdate(Dish dish) {
        this.name = dish.getName();
        this.price = dish.getPrice();
    }
}
