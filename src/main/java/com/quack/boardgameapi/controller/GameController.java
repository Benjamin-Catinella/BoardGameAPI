package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.gamedata.GameCreationParams;
import com.quack.boardgameapi.service.GameService;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;

import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class GameController {
    private GameService gameService;
    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    @GetMapping("/games")
    public Collection<Game> getGames() {
        return gameService.getGames();
    }
    @GetMapping("/games/types")
    public List<String> getGameIds(){
        return gameService.getGameIds();
    }
    @GetMapping("/games/{uuid}/tokens/available")
    public Collection<Token> getAvailableTokens(@PathVariable("uuid") String uuid) {
        return gameService.getAvailableTokens(uuid);
    }
    /**
     * Moves the specified token in said position and returns the new collection of available tokens
     */
    @PutMapping("/games/{gameUUID}/tokens/available")
    public Collection<Token> moveToken(@PathVariable("gameUUID") String uuid, @RequestBody CellPosition[] cellPositions) {
        return gameService.moveToken(uuid,cellPositions);
    }
//    @GetMapping("/test/language")
//    public Locale testLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {
//        return Locale.forLanguageTag(language);
//    }
    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams) {
        return gameService.createGame(gameCreationParams);
    }

}
