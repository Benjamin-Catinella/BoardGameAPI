package com.quack.boardgameapi.service;

import com.quack.boardgameapi.gamedata.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Collection;
import java.util.List;

public interface GameService {

    Collection<Game> getGames();

    List<String> getGameIds();

    Collection<Token> getAvailableTokens(String uuid);

    /**
     * Moves the specified token in said position and returns the new collection of available tokens
     */
    Collection<Token> moveToken(String uuid, CellPosition[] cellPositions);

    String createGame(GameCreationParams gameCreationParams);
}
