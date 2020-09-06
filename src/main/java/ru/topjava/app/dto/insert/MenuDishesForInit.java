package ru.topjava.app.dto.insert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuDishesForInit {
    private UUID dish;
    private UUID restaurant;
}

