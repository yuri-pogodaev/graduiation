package ru.topjava.app.testdata;

import ru.topjava.app.entity.Dish;

import java.math.BigDecimal;
import java.util.UUID;

public class DishTestData {
    public static final UUID DISH_1_ID = UUID.fromString("06d18c89-1461-48ad-bcfa-a726a51d3ec3");
    public static final UUID DISH_2_ID = UUID.fromString("09f79f73-5d25-4461-85d8-2e1bdf9420e9");
    public static final UUID DISH_3_ID = UUID.fromString("0ba6a0ef-9886-4e05-92f6-7dd729eaf7fd");
    public static final UUID DISH_4_ID = UUID.fromString("14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd");
    public static final UUID DISH_5_ID = UUID.fromString("1d9d6ead-3c29-494d-8e58-f3efd42c59bf");
    public static final UUID DISH_6_ID = UUID.fromString("799ec7f9-20f5-4d29-b6d1-e0ee3ceb8d9c");
    public static final UUID DISH_7_ID = UUID.fromString("87cca8f6-c1c8-4fe1-bb63-ea0eb7249ccf");
    public static final UUID DISH_8_ID = UUID.fromString("9a7fb2f6-6fb7-4689-9ad9-27fc8e095055");
    public static final UUID DISH_9_ID = UUID.fromString("9b0a7e1c-c6eb-47ec-9be9-f3f05824461e");
    public static final UUID DISH_10_ID = UUID.fromString("a5812fcf-c878-4074-88f9-5acd552a137e");
    public static final UUID DISH_11_ID = UUID.fromString("d11a4a44-e79b-4745-9a4f-eecfba225e89");
    public static final UUID DISH_12_ID = UUID.fromString("f649a320-e50c-46e8-9657-d433f32734cf");

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "Салат Цезарь", new BigDecimal(500));
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "Грибной суп", new BigDecimal(1200));
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "Стейк", new BigDecimal(2100));
    public static final Dish DISH_4 = new Dish(DISH_4_ID, "Салат из морепродуктов", new BigDecimal(1100));
    public static final Dish DISH_5 = new Dish(DISH_5_ID, "Суп том-ям", new BigDecimal(530));
    public static final Dish DISH_6 = new Dish(DISH_6_ID, "Лапша с курицей", new BigDecimal(2100));
    public static final Dish DISH_7 = new Dish(DISH_7_ID, "Салат оливье", new BigDecimal(320));
    public static final Dish DISH_8 = new Dish(DISH_8_ID, "Борщ", new BigDecimal(550));

    public static Dish getNew() {
        return new Dish(UUID.randomUUID(), "new", new BigDecimal(1000));
    }

    public static Dish getUpdated() {
        return new Dish(DISH_1_ID,"Какое-то блюдо", new BigDecimal(190));
    }
}
