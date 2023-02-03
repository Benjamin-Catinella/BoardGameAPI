package com.quack.boardgameapi.service.interfaces;

import com.quack.boardgameapi.gamedata.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface GameService {

    //TODO: Change this so it's cleaner
    Collection<Game> getGames();

    Collection<String> getGamesUUIDs();

    List<String> getGameIds();

    Collection<Token> getAvailableTokens(String uuid);

    /**
     * Moves the specified token in said position and returns the new collection of available tokens
     */
    Collection<Token> moveToken(String uuid, CellPosition[] cellPositions);

    String createGame(GameCreationParams gameCreationParams, Locale locale);

}
