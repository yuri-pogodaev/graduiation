DELETE
FROM VOTES;
DELETE
FROM DISHES;
DELETE
FROM USERS;
DELETE
FROM RESTAURANTS;
DELETE
FROM MENU_DISHES;
INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD, REGISTERED, ROLE)
VALUES ('6a1fd295-66c4-490b-b8de-24b8029a4db9', 'User1', 'user1@yandex.ru',
        '$2a$10$.Rxx4JnuX8OGJTIOCXn76euuB3dIGHHrkX9tswYt9ECKjAGyms30W', '2020-07-06', 'USER'),
       ('932ad7b5-84df-413c-a5ba-c12fddd469ec', 'User2', 'user2@yandex.ru',
        '$2a$10$.Rxx4JnuX8OGJTIOCXn76euuB3dIGHHrkX9tswYt9ECKjAGyms30W', '2020-07-06', 'USER'),
       ('b063b2ba-f191-43fa-bf4d-531bf1996134', 'User3', 'user3@yandex.ru',
        '$2a$10$.Rxx4JnuX8OGJTIOCXn76euuB3dIGHHrkX9tswYt9ECKjAGyms30W', '2020-07-06', 'USER'),
       ('b71db295-2f71-45e2-be20-2be6c65624a8', 'Admin', 'admin@gmail.com',
        '$2a$10$.Rxx4JnuX8OGJTIOCXn76euuB3dIGHHrkX9tswYt9ECKjAGyms30W', '2020-07-06', 'ADMIN');



INSERT INTO RESTAURANTS (ID, NAME, ADDRESS)
VALUES ('44569c6f-c11f-4d0c-8578-b57b736bc079', 'Европейский ресторан', 'Ленинский пр-т, д. 1'),
       ('7ea5de3a-1846-4bf2-9151-d1bfccf716a5', 'Тайский ресторан', 'Тверская ул., д. 10'),
       ('cf14de54-3c75-41c6-a05f-9a87b18d1fe5', 'Русский ресторан', 'Площадь им. Ленина, д. 18');

INSERT INTO DISHES (ID, NAME, PRICE)
VALUES ('06d18c89-1461-48ad-bcfa-a726a51d3ec3', 'Салат Цезарь', 500),
       ('09f79f73-5d25-4461-85d8-2e1bdf9420e9', 'Грибной суп', 1200),
       ('0ba6a0ef-9886-4e05-92f6-7dd729eaf7fd', 'Стейк', 2100),

       ('14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd', 'Салат из морепродуктов', 1100),
       ('1d9d6ead-3c29-494d-8e58-f3efd42c59bf', 'Суп том-ям', 530),
       ('799ec7f9-20f5-4d29-b6d1-e0ee3ceb8d9c', 'Лапша с курицей', 2100),

       ('87cca8f6-c1c8-4fe1-bb63-ea0eb7249ccf', 'Салат оливье', 320),
       ('9a7fb2f6-6fb7-4689-9ad9-27fc8e095055', 'Борщ', 550),
       ('9b0a7e1c-c6eb-47ec-9be9-f3f05824461e', 'Котлеты с картофелем', 700),

       ('a5812fcf-c878-4074-88f9-5acd552a137e', 'Пицца', 300),
       ('d11a4a44-e79b-4745-9a4f-eecfba225e89', 'Томатный суп', 270),
       ('f649a320-e50c-46e8-9657-d433f32734cf', 'Паста', 290);

INSERT INTO MENU_DISHES (ID,UPDATED_AT, DISH_ID, RESTAURANT_ID)
VALUES ('d2f63f20-da39-4606-9aae-1c492d3c4a42','2020-07-06', '06d18c89-1461-48ad-bcfa-a726a51d3ec3', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('8b247ae8-fd3e-46e0-a279-24bc1de2be80','2020-07-06', '09f79f73-5d25-4461-85d8-2e1bdf9420e9', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('fab78154-4fc7-46ea-b09b-cb95e838489b','2020-07-06', '0ba6a0ef-9886-4e05-92f6-7dd729eaf7fd', '44569c6f-c11f-4d0c-8578-b57b736bc079'),

       ('0ea8de5f-89c6-4534-bae7-106a6a75c28a','2020-07-07', '14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
       ('658f0785-8b65-4e75-bc09-15b2960f0a83','2020-07-07', '1d9d6ead-3c29-494d-8e58-f3efd42c59bf', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
       ('8a5829b4-7254-43e5-b826-edde2c4ab183','2020-07-07', '799ec7f9-20f5-4d29-b6d1-e0ee3ceb8d9c', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),

       ('0342db94-cd96-4b60-aa8a-5da33a1f6316','2020-07-08', '87cca8f6-c1c8-4fe1-bb63-ea0eb7249ccf', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),
       ('63ef5e69-b490-4d2e-aece-e33023762b77','2020-07-08', '9a7fb2f6-6fb7-4689-9ad9-27fc8e095055', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),
       ('de55479a-6d96-4af3-afdf-a0b2ec38c2c0','2020-07-08', '9b0a7e1c-c6eb-47ec-9be9-f3f05824461e', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),

       ('f9278304-3129-4bcd-a006-8c98eb2e1c52','2020-07-09', 'a5812fcf-c878-4074-88f9-5acd552a137e', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('fea3f331-8a69-4062-9c5c-66e9a1564833','2020-07-09', 'd11a4a44-e79b-4745-9a4f-eecfba225e89', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('15fdbe43-77c4-4fd2-a18c-ca78fec86574','2020-07-09', 'f649a320-e50c-46e8-9657-d433f32734cf', '44569c6f-c11f-4d0c-8578-b57b736bc079');

INSERT INTO VOTES (ID, UPDATED_AT, USER_ID, RESTAURANT_ID)
VALUES ('d8a0afe8-38f8-456c-b866-a1e845c4c429', '2020-07-06', '6a1fd295-66c4-490b-b8de-24b8029a4db9',
        '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('3917d6e6-c839-4b79-9cb0-3137be35f639', '2020-07-06', '932ad7b5-84df-413c-a5ba-c12fddd469ec',
        '44569c6f-c11f-4d0c-8578-b57b736bc079'),
       ('c4b76caf-443b-4c55-b527-3457304039c7', '2020-07-06', 'b063b2ba-f191-43fa-bf4d-531bf1996134',
        '7ea5de3a-1846-4bf2-9151-d1bfccf716a5');