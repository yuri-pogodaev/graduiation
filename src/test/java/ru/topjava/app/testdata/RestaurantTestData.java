package ru.topjava.app.testdata;

import ru.topjava.app.entity.Dish;
import ru.topjava.app.entity.Restaurant;

import java.math.BigDecimal;
import java.util.UUID;

public class RestaurantTestData {
    public static final UUID RESTAURANT_1_ID = UUID.fromString("06d18c89-1461-48ad-bcfa-a726a51d3ec3");
    public static final UUID RESTAURANT_2_ID = UUID.fromString("7ea5de3a-1846-4bf2-9151-d1bfccf716a5");
    public static final UUID RESTAURANT_3_ID = UUID.fromString("cf14de54-3c75-41c6-a05f-9a87b18d1fe5");

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Европейский ресторан", "Ленинский пр-т, д. 1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Тайский ресторан", "Тверская ул., д. 10");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_3_ID, "Русский ресторан", "Площадь им. Ленина, д. 18");

    public static Restaurant getNew() {
        return new Restaurant(UUID.randomUUID(), "новый ресторан", "новый адрес");
    }
    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_1_ID,"Какой-то ресторан", "Какой-то адрес");
    }
}
