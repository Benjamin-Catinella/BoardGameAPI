package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.data.dto.GameWrapper;
import com.quack.boardgameapi.gamedata.GameCreationParams;
import com.quack.boardgameapi.service.interfaces.GameService;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/public")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public Collection<Game> getGames() {
        return gameService.getGames();
    }
    @GetMapping("/games/uuids")
    public Collection<String> getGamesUUIDs() {
        return gameService.getGamesUUIDs();
    }

    @GetMapping("/games/types")
    public List<String> getGameIds() {
        return gameService.getGameIds();
    }

    @GetMapping("/games/{uuid}/tokens/available")
    public Collection<Token> getAvailableTokens(@PathVariable("uuid") String uuid) {
        return gameService.getAvailableTokens(uuid);
    }

    /**
     * Moves the specified token in said position and returns the new collection of available tokens an updates the game save
     */
    @PutMapping("/games/{gameUUID}/tokens/available")
    public Collection<Token> moveToken(@PathVariable("gameUUID") String uuid, @RequestBody CellPosition[] cellPositions) {
        return gameService.moveToken(uuid, cellPositions);
    }


    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams, Locale locale) {
        return gameService.createGame(gameCreationParams, locale);
    }



}
