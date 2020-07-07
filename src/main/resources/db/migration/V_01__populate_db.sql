DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM USERS;
DELETE FROM RESTAURANTS;
DELETE FROM MENU_DISHES;
INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD, IS_ADMIN, REGISTERED) VALUES
('6a1fd295-66c4-490b-b8de-24b8029a4db9' ,'User1', 'user1@yandex.ru', 'password1111', false, '2020-07-06'),
('932ad7b5-84df-413c-a5ba-c12fddd469ec','User2', 'user2@yandex.ru', 'password2', false, '2020-07-06'),
('b063b2ba-f191-43fa-bf4d-531bf1996134','User3', 'user3@yandex.ru', 'password3',false, '2020-07-06'),
('b71db295-2f71-45e2-be20-2be6c65624a8','Admin', 'admin@gmail.com', 'admin', true, '2020-07-06');

INSERT INTO RESTAURANTS (ID, NAME, ADDRESS) VALUES
('44569c6f-c11f-4d0c-8578-b57b736bc079','Европейский ресторан', 'Ленинский пр-т, д. 1'),
('7ea5de3a-1846-4bf2-9151-d1bfccf716a5' ,'Тайский ресторан', 'Тверская ул., д. 10'),
('cf14de54-3c75-41c6-a05f-9a87b18d1fe5' ,'Русский ресторан', 'Площадь им. Ленина, д. 18');

INSERT INTO DISHES (ID , NAME, PRICE) VALUES
('06d18c89-1461-48ad-bcfa-a726a51d3ec3' ,'Салат Цезарь', 500),
('09f79f73-5d25-4461-85d8-2e1bdf9420e9' ,'Грибной суп', 1200),
('0ba6a0ef-9886-4e05-92f6-7dd729eaf7fd' ,'Стейк', 2100),

('14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd' ,'Салат из морепродуктов', 1100),
('1d9d6ead-3c29-494d-8e58-f3efd42c59bf' ,'Суп том-ям', 530),
('799ec7f9-20f5-4d29-b6d1-e0ee3ceb8d9c' ,'Лапша с курицей', 2100),

('87cca8f6-c1c8-4fe1-bb63-ea0eb7249ccf' ,'Салат оливье', 320),
('9a7fb2f6-6fb7-4689-9ad9-27fc8e095055' ,'Борщ', 550),
('9b0a7e1c-c6eb-47ec-9be9-f3f05824461e' ,'Котлеты с картофелем', 700),

('a5812fcf-c878-4074-88f9-5acd552a137e' ,'Пицца', 300),
('d11a4a44-e79b-4745-9a4f-eecfba225e89' ,'Томатный суп', 270),
('f649a320-e50c-46e8-9657-d433f32734cf' ,'Паста', 290);

INSERT INTO MENU_DISHES (UPDATED_AT, DISH_ID, RESTAURANT_ID) VALUES
('2020-07-06' ,'06d18c89-1461-48ad-bcfa-a726a51d3ec3' , '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-06' ,'09f79f73-5d25-4461-85d8-2e1bdf9420e9' , '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-06', '0ba6a0ef-9886-4e05-92f6-7dd729eaf7fd', '44569c6f-c11f-4d0c-8578-b57b736bc079'),

('2020-07-07', '14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
('2020-07-07', '1d9d6ead-3c29-494d-8e58-f3efd42c59bf', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
('2020-07-07', '799ec7f9-20f5-4d29-b6d1-e0ee3ceb8d9c', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),

('2020-07-08', '87cca8f6-c1c8-4fe1-bb63-ea0eb7249ccf', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),
('2020-07-08', '9a7fb2f6-6fb7-4689-9ad9-27fc8e095055', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),
('2020-07-08', '9b0a7e1c-c6eb-47ec-9be9-f3f05824461e', 'cf14de54-3c75-41c6-a05f-9a87b18d1fe5'),

('2020-07-09', 'a5812fcf-c878-4074-88f9-5acd552a137e', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-09', 'd11a4a44-e79b-4745-9a4f-eecfba225e89', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-09', 'f649a320-e50c-46e8-9657-d433f32734cf', '44569c6f-c11f-4d0c-8578-b57b736bc079');

INSERT INTO VOTES (UPDATED_AT, USER_ID, RESTAURANT_ID) VALUES
('2020-07-06', '6a1fd295-66c4-490b-b8de-24b8029a4db9', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-06', '932ad7b5-84df-413c-a5ba-c12fddd469ec', '44569c6f-c11f-4d0c-8578-b57b736bc079'),
('2020-07-06', 'b063b2ba-f191-43fa-bf4d-531bf1996134', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
('2020-07-07', '6a1fd295-66c4-490b-b8de-24b8029a4db9', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
('2020-07-07', '932ad7b5-84df-413c-a5ba-c12fddd469ec', '7ea5de3a-1846-4bf2-9151-d1bfccf716a5'),
('2020-07-07', 'b063b2ba-f191-43fa-bf4d-531bf1996134', '44569c6f-c11f-4d0c-8578-b57b736bc079');