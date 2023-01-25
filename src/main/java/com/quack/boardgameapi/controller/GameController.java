package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.GameCollection;
import com.quack.boardgameapi.GameCreationParams;
import com.quack.boardgameapi.GamePlugins;
import com.quack.boardgameapi.MessageSourceAlt;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GameController {
    /*
    Demander pour le plugin et l'utilité de mettre tous les autowire
     */
    @Autowired
    GameCollection gameCollection;

    @Autowired
    GamePlugins plugins;

    @GetMapping("/games")
    public Object getGames() {
        return gameCollection.getCollection();
    }

    @GetMapping("/games/{uuid}/tokens/available")
    public Collection<Token> getAvailableTokens(@PathVariable("uuid") String uuid) {
        Game game = gameCollection.getGame(uuid);
        Map<CellPosition,Token> board = game.getBoard();
        Collection<Token> tokens = board.keySet().stream()
                .map(key -> board.get(key))
                .filter(token -> token.canMove())
                .toList();
        return tokens;
    }

    /**
     * Moves the specified token in said position and returns the new collection of available tokens
     */
    @PutMapping("/games/{gameUUID}/tokens/available")
    public Collection<Token> moveToken(
            @PathVariable("gameUUID") String uuid,
            @RequestBody CellPosition[] cellPositions
    ) {
        CellPosition oldPosition = cellPositions[0];
        CellPosition newPosition = cellPositions[1];
        Game game = gameCollection.getGame(uuid);
        Optional<Token> token;
        Map<CellPosition,Token> board = game.getBoard();
        Stream<Token> tokens = board.keySet().stream()
                .map(key -> board.get(key))
                .filter(boardToken -> boardToken.canMove());
        if(oldPosition == null){
            token = tokens.filter(colToken -> colToken.getPosition() == null).findFirst();
        }else {
            token = tokens.filter(colToken -> colToken.getPosition().equals(oldPosition)).findFirst();
        }
        try {
            if (token.isPresent()) {
                token.get().moveTo(newPosition);
            }
            else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Could not get token with these parameters"
                );
            }
        } catch (InvalidPositionException invalidPositionException) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Position invalid"
            );
        }
        return game.getRemainingTokens();
    }

    @GetMapping("/test/language")
    public Locale testLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {
        return Locale.forLanguageTag(language);
    }

    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams) {

        String gameType = gameCreationParams.gameType();

        Game game = plugins.getPluginByGameId(gameType)
                .getFactoryInstance()
                .createGame(
                        gameCreationParams.numberOfPlayers(),
                        gameCreationParams.boardSize()
                );
        String gameUUID = gameCollection.addGame(game);
        return gameUUID;
    }

}
