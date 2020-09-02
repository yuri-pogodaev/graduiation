package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Dish;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DishForResponse {
//    @JsonSerialize(using= UUIDSerializer.class)
//    @JsonDeserialize(using= UUIDDeserializer.class)
    private UUID id;
    private String name;
    private BigDecimal price;

    public DishForResponse(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
    }
}
