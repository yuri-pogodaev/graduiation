## TopJava graduation project


Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
The task is:
Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we asume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.
It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

## Running the application
Just run Application
### Application description
* Admin can do:
    - viewing, adding, updating and deleting users. If user has votes it can't be deleted.
    - viewing, adding, updating, deleting restaurants. If restaurant has votes or menus it can't be deleted.
    - viewing, adding, updating, deleting restaurant's menu. If menu has dishes it can't be deleted.
    - viewing, adding, updating, deleting menu's dishes.
 * User can do:
    - viewing restaurants, it's lunch menu and dishes for today.
    - voting and update vote for a restaurant before 11:00.
## Add Postman collection for test API
    

### Curl commands REST API
### Admin API
#### Admin Profile API

| Role | Path                 |  Method | Description   |
|------|----------------------|---------|---------------|
| Admin| [`/admin/users`]|   `GET` | Get all users |
* **Example:**

`curl -s http://localhost:8080/admin/users --user admin@gmail.com:pass`

| Role | Path                      |  Method | Description    |
|------|---------------------------|---------|----------------|
| Admin| [`/admin/users/{id}`]|   `GET` | Get user by id |
* **Example:**

`curl -s http://localhost:8080/admin/users/6a1fd295-66c4-490b-b8de-24b8029a4db9 --user admin@gmail.com:pass`

| Role | Path                 |  Method  | Description |
|------|----------------------|----------|-------------|
| Admin| [`/admin/users`]|   `POST` | Create user |
* **Example:**

`curl -H 'Content-Type: application/json' -X POST http://localhost:8080/admin/users --user admin@gmail.com:pass -d '{"name":"New User","email":"1232@gmail.com","password":"password", "registred":"2020-07-06","role":"USER"}'`

| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/admin/users/{id}`]|   `PUT` | Update user |
* **Example:**

`curl -s -X PUT -d '{"name":"Update user","email":"123222@gmail.com","password":"password"} -H 'Content-type: application/json' http://localhost:8080/admin/users/6a1fd295-66c4-490b-b8de-24b8029a4db9 --user admin@gmail.com:pass`


| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/admin/users/{id}`]|`DELETE` | Delete user |
* **Example:**

`curl -s -X DELETE http://localhost:8080/admin/users/6a1fd295-66c4-490b-b8de-24b8029a4db9 --user admin@gmail.com:pass`

#### Admin Restaurant API

| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| Admin| [`/admin/restaurants`]  |   `GET` | Get all restaurants |
* **Example:**

`curl -s http://localhost:8080/admin/restaurants --user admin@gmail.com:pass`

| Role | Path                              |  Method | Description          |
|------|-----------------------------------|---------|----------------------|
| Admin| [`/admin/restaurants/{id}`]  |   `GET` | Get restaurant by id |
* **Example:**

`curl -s http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079 --admin@gmail.com:pass`

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/admin/restaurants`]|   `POST` | Create restaurant |
* **Example:**

`curl -s -X POST -d '{"name":"New restaurant name", "address":"New restaurant address"}' -H 'Content-Type:application/json' http://localhost:8080/admin/restaurants --user admin@gmail.com:pass`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/admin/restaurants/{id}`]|   `PUT` | Update restaurant |
* **Example:**

`curl -s -X PUT -d '{"name":"Update name restaurant", "address":"Update address restaurant"}' -H 'Content-type: application/json' http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079 --user admin@gmail.com:pass`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/admin/restaurants/{id}`]|`DELETE` | Delete restaurant |
* **Example:**

`curl -s -X DELETE http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079 --user admin@gmail.com:pass`

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/admin/restaurants/{id}/menus`]|   `POST` | Create menu |
* **Example:**

`curl -s -X POST -d '{"dish":"06d18c89-1461-48ad-bcfa-a726a51d3ec3","restaurant":"44569c6f-c11f-4d0c-8578-b57b736bc079"}' -H 'Content-Type:application/json' http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus --user admin@gmail.com:pass`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}`]|   `PUT` | Update menu |
* **Example:**

`curl -s -X PUT -d '{"restaurant":"7ea5de3a-1846-4bf2-9151-d1bfccf716a5","dish":"14a4b0ac-8ae1-4d12-a86d-2b9fd487c7bd"}' -H 'Content-type: application/json' http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/fea3f331-8a69-4062-9c5c-66e9a1564833 --user admin@gmail.com:pass`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/admin/restaurants/{id}/menus/{id}`]|`DELETE` | Delete menu |
* **Example:**

`curl -s -X DELETE http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/d2f63f20-da39-4606-9aae-1c492d3c4a42 --user admin@gmail.com:pass`
| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| Admin| [`/admin/restaurants/{id}/menus`]  |   `GET` | Get all menu |
* **Example:**

`curl -s http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus --user admin@gmail.com:pass`

| Role | Path                              |  Method | Description          |
|------|-----------------------------------|---------|----------------------|
| Admin| [`/admin/restaurants/{id}/menus/{id}`]  |   `GET` | Get menu by id |
* **Example:**

`curl -s http://localhost:8080/admin/restaurants/44569c6f-c11f-4d0c-8578-b57b736bc079/menus/d2f63f20-da39-4606-9aae-1c492d3c4a42 --admin@gmail.com:pass`

### User API
#### User Profile API

| Role | Path                 |  Method  | Description |
|------|----------------------|----------|-------------|
| User| [`/profile/register`]|   `POST` | Register new user |
* **Example:**
`curl -s -X POST -d '{"name":"New User","email":"1232@gmail.com","password":"password", "registred":"2020-07-06","role":"USER"}' -H 'Content-Type:application/json' http://localhost:8080/profile/register --user user1@yandex.ru:pass`

#### User Restaurant API
| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| User| [`/restaurants`]  |   `GET` | Get all restaurants with dishes for today |
* **Example:**


#### User Vote API
| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| User| [`/vote`]|`POST` | Vote for restaurant |
* **Example:**
`curl -s -X POST -d '{"user":"6a1fd295-66c4-490b-b8de-24b8029a4db9","restaurant":"44569c6f-c11f-4d0c-8578-b57b736bc079","updatedAt":"2020-07-21"}' -H 'Content-Type:application/json' http://localhost:8080/vote --user user1@yandex.ru:pass`
