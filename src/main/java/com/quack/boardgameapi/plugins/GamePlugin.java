package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.gamedata.CreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;

/**
 * GamePlugin interface, every class that implements this interface should have a GameFactory field of it's corresponding game
 * It also must have fields corresponding to the default values of the game.
 */
public interface GamePlugin {
    /**
     * Uses @Value fields in the class to return an instance of a CreationParams object containing the default
     * parameters for a game
     * @return CreationParams object
     */
    CreationParams getDefaultParams();

    /**
     * Returns the GameFactory instance corresponding to the implementation of the class.
     * eg: TicTacToePlugin returns it's instance of the TicTacToeGameFactory
     * @return
     */
    GameFactory getFactoryInstance();

    /**
     * Gets the corresponding game id (loaded from the config file) as a string.
     * eg: Tic tac toe shall be equal to "TicTacToe"
     */
    String gameId();
}
