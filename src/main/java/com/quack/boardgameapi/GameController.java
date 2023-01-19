package com.quack.boardgameapi;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
public class GameController {
    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams){
        return UUID.randomUUID().toString();
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId){
        return null;
    }
}
