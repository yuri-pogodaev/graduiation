package ru.topjava.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.app.dto.insert.UserForInit;
import ru.topjava.app.dto.response.UserForResponse;
import ru.topjava.app.dto.update.UserForUpdate;
import ru.topjava.app.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.topjava.app.controller.AdminRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    public static final String REST_URL = "/admin/users";
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UUID> save(@RequestBody @Valid UserForInit userForInit) {
        UUID created = userService.init(userForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid UserForUpdate userForUpdate) throws Exception {
        userService.update(userForUpdate, id);
    }

    @GetMapping("/{id}")
    public UserForResponse getById(@PathVariable("id") UUID id) throws Exception {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserForResponse> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws Exception {
        userService.deleteById(id);
    }
}
