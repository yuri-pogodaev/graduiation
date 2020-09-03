package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.app.dto.insert.VoteForInit;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.dto.update.VoteForUpdate;
import ru.topjava.app.entity.Vote;
import ru.topjava.app.service.VoteService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/all")
    public List<VoteForResponse> getAll() {
        return voteService.getAll();
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid VoteForUpdate voteForUpdate) throws Exception {
//        voteService.update(voteForUpdate, id);
    }

    @GetMapping("/{restaurantId}/{userId}")
    public VoteForResponse getById(@PathVariable UUID restaurantId, @PathVariable UUID userId) {
        return voteService.getById(restaurantId, userId);
    }

    @PostMapping("/save")
    public UUID save(@RequestBody @Valid VoteForInit voteForInit) throws Exception {
        Vote created = voteService.createNewVote(voteForInit);

        return created.getId();
    }

}
