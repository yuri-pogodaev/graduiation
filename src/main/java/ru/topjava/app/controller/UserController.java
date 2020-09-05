package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.insert.UserForInit;
import ru.topjava.app.dto.response.UserForResponse;
import ru.topjava.app.dto.update.UserForUpdate;
import ru.topjava.app.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public List<UserForResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")

    public UserForResponse getById(@PathVariable("id") UUID id) {
        try {
            return userService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid UserForUpdate userForUpdate) throws Exception {
        userService.update(userForUpdate, id);
    }


    @PostMapping("/save")
    public UUID save(@RequestBody @Valid UserForInit userForInit) {
        return userService.init(userForInit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) throws Exception {
        userService.deleteById(id);
    }
}
