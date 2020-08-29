package ru.topjava.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.app.dto.response.VoteForResponse;
import ru.topjava.app.service.VoteService;

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

//    @PostMapping("/update/{id}")
//    public void update(@PathVariable("id") UUID id, @RequestBody @Valid VoteForUpdate voteForUpdate) throws Exception {
//        voteService.update(voteForUpdate, id);
//    }

    @GetMapping("/{restaurantId}/{userId}")
    public VoteForResponse getById(@PathVariable UUID restaurantId, @PathVariable UUID userId){
        return voteService.getById(restaurantId, userId);
    }
}
