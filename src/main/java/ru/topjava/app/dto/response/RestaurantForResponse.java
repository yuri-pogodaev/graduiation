package ru.topjava.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.topjava.app.entity.Restaurant;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RestaurantForResponse {
    private UUID id;
    private String name;
    private String address;

    public RestaurantForResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
    }
}
