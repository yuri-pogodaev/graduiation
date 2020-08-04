DROP TABLE IF EXISTS VOTES;
DROP TABLE IF EXISTS MENU_DISHES;
DROP TABLE IF EXISTS USERES;
DROP TABLE IF EXISTS RESTAURANTS;
DROP TABLE IF EXISTS DISHES;

CREATE TABLE USERS
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(50)             NOT NULL,
    email      VARCHAR(50)             NOT NULL,
    password   VARCHAR(50)             NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    is_admin   BOOLEAN   DEFAULT FALSE NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE RESTAURANTS
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE DISHES
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    price NUMERIC     NOT NULL
);

CREATE TABLE MENU_DISHES
(
    updated_at    TIMESTAMP default now() NOT NULL,
    dish_id       UUID                    NOT NULL,
    restaurant_id UUID                    NOT NULL,
    CONSTRAINT MENU_DISHES_ID PRIMARY KEY (dish_id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES DISHES (id) ON DELETE CASCADE
);
CREATE INDEX menu_date_idx ON MENU_DISHES (updated_at);

CREATE TABLE VOTES
(
    updated_at    TIMESTAMP default now() NOT NULL,
    user_id       UUID                   ,
    restaurant_id UUID                   ,
    CONSTRAINT VOTES_ID PRIMARY KEY (user_id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);