package com.quack.boardgameapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {
    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams){
        return gameCreationParams.toString();
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId){
        return null;
    }

}
