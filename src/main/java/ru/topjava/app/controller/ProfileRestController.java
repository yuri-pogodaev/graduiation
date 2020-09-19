package ru.topjava.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.app.dto.insert.UserForInit;
import ru.topjava.app.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static ru.topjava.app.controller.ProfileRestController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class ProfileRestController {
    public static final String REST_URL = "/profile";

    private final UserService userService;

    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UUID> register(@Valid @RequestBody UserForInit userForInit) {
        UUID created = userService.init(userForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created)
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
//todo need update,get,delete with @AuthenticationPrincipal

}
