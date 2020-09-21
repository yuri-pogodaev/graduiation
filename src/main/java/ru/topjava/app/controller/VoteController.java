package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.insert.VoteForInit;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.entity.Vote;
import ru.topjava.app.service.VoteService;

import javax.validation.Valid;
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

    @GetMapping("/history/{userId}")
    public List<VoteForResponse> getHistory(@PathVariable UUID userId) {
        return voteService.getHistory(userId);
    }


    @GetMapping("/{id}")
    public VoteForResponse getById(@PathVariable("id") UUID id) throws Exception {
        return voteService.getById(id);
    }

    @PostMapping("/save")
    public UUID save(@RequestBody @Valid VoteForInit voteForInit) throws Exception {
        Vote created = voteService.createNewVote(voteForInit);
        return created.getId();
    }
}
