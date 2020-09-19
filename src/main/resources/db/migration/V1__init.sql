DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS menu_dish;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS dish;

CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(50)             NOT NULL,
    email      VARCHAR(50)             NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    role       varchar(15)             NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurant
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    address VARCHAR(255) NOT NULL,
);
CREATE UNIQUE INDEX restaurant_unique_name_idx ON restaurant (name);

CREATE TABLE dish
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    price NUMERIC     NOT NULL
);
CREATE UNIQUE INDEX dish_unique_name_dish_idx
    ON dish (name);

CREATE TABLE menu_dish
(
    id            UUID primary key,
    updated_at    TIMESTAMP default now() NOT NULL,
    dish_id       UUID,
    restaurant_id UUID,
--     CONSTRAINT MENU_DISHES_ID PRIMARY KEY (dish_id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            UUID primary key,
    updated_at    TIMESTAMP default now() NOT NULL,
    user_id       UUID,
    restaurant_id UUID,
--     CONSTRAINT VOTES_ID PRIMARY KEY (user_id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_idx
    ON vote (user_id, updated_at);