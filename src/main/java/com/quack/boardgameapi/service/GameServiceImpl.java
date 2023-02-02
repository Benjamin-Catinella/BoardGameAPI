package com.quack.boardgameapi.service;

import com.quack.boardgameapi.controller.GamePluginsMap;
import com.quack.boardgameapi.factory.GameFactory;
import com.quack.boardgameapi.gamedata.GameCreationParams;

import com.quack.boardgameapi.repository.GameSaveRepository;
import com.quack.boardgameapi.service.interfaces.GameService;
import com.quack.boardgameapi.service.interfaces.GamePersistenceEngine;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    private GamePersistenceEngine gamePersistenceEngine;
    @Autowired
    private GameSaveRepository gameSaveRepository;
    private GamePluginsMap plugins;
    private TranslationService translationService;
    @Autowired
    public GameServiceImpl(GamePluginsMap plugins, TranslationService translationService , GamePersistenceEngine gamePersistenceEngine) {
        this.gamePersistenceEngine = gamePersistenceEngine;
        this.plugins = plugins;
        this.translationService = translationService;
    }

    @Override
    public Collection<Game> getGames() {
        return gamePersistenceEngine.getAll();
    }

    @Override
    public List<String> getGameIds() {
        return plugins.getPluginMap().stream().map(gamePlugin -> gamePlugin.gameId()).toList();
    }

    /**
     * Fetches all the tokens that are available to move for a given game uuid
     *
     * @param uuid
     * @return Collection of Tokens
     */
    @Override
    public Collection<Token> getAvailableTokens(@PathVariable("uuid") String uuid) {
        Game game = (Game) gamePersistenceEngine.read(uuid);
        Map<CellPosition, Token> board = game.getBoard();
        Collection<Token> tokens = board.keySet().stream()
                .map(key -> board.get(key))
                .filter(token -> token.canMove())
                .toList();
        return tokens;
    }

    /**
     * Moves the specified token in said position and returns the new collection of available tokens
     *
     * @return The new available tokens after the prior has been moved
     */
    @Override
    public Collection<Token> moveToken(String uuid, CellPosition[] cellPositions) {
        CellPosition oldPosition = cellPositions[0];
        CellPosition newPosition = cellPositions[1];
        Game game = gamePersistenceEngine.read(uuid);
        Optional<Token> token;

        // Gets the entire board
        Map<CellPosition, Token> board = game.getBoard();

        // Gets all Tokens that can be moved
        Stream<Token> tokens = board.keySet().stream()
                .map(key -> board.get(key))
                .filter(boardToken -> boardToken.canMove());

        if (oldPosition == null) {
            token = tokens.filter(colToken -> colToken.getPosition() == null).findFirst();
        }
        else {
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

    /**
     * Gets the corresponding factory for the given game type, creates the associated game,
     * adds it to the collection and returns
     *
     * @param gameCreationParams
     * @return IDK I'LL HAVE TO CHANGE THIS LATER
     * TODO: Change return type later
     */
    @Override
    public String createGame(GameCreationParams gameCreationParams, Locale locale) {

        String gameType = gameCreationParams.gameType();

        Game game = plugins.getPluginByGameId(gameType)
                .getFactoryInstance()
                .createGame(
                        gameCreationParams.numberOfPlayers(),
                        gameCreationParams.boardSize()
                );
        game = gamePersistenceEngine.create(game);
        String gameKey = "Game." + gameCreationParams.gameType() + ".name";
//        return gameUUID += translationService.translate(gameKey, locale);
        return gameKey;
    }

    /**
     * Loads a game from a save
     *
     * @param gameCreationParams
     * @param locale
     * @param saveID
     * @return
     */
    @Override
    public Game loadGameFromSaveID(Long saveID) {
        return GameFactory.from(gameSaveRepository.getById(saveID));
    }

}
