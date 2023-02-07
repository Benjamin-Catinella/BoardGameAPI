package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.service.interfaces.UserStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.ConnectException;
import java.util.UUID;

@RestController
@RequestMapping("api/public/stats")
public class UserStatsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsController.class);
    private final UserStatsService userStatsService;
    @Autowired
    private UserStatsController(UserStatsService userStatsService){
        this.userStatsService = userStatsService;
    }

    @GetMapping("/{uuid}/wins")
    public int getUserWins(@PathVariable("uuid")UUID uuid){
        return userStatsService.getUserWins(uuid);
    }

    @GetMapping("/{uuid}/loses")
    public int getUserLoses(@PathVariable("uuid")UUID uuid){
        return userStatsService.getUserLoses(uuid);
    }
}
