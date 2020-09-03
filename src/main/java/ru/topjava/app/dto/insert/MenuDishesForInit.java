package ru.topjava.app.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.Restaurant;

import java.time.LocalDate;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuDishesForInit {
//    private UUID id;
    private UUID dish;
    private UUID restaurant;

//    private LocalDate updatedAt;
}

