package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.GameSaveEntity;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(GameFactory.class);

    /**
     * Loads a game from a save as a Game Object
     * @param save
     * @return
     * TODO: Change this later by adding reflections
     */
    public static Game from(@NotNull GameSaveEntity save){
        fr.le_campus_numerique.square_games.engine.GameFactory gameFactory;
        // Game name is Game class
        LOGGER.debug(save.toString());

        switch (save.getGameName()){
            case "TicTacToeGame":
                LOGGER.debug("Set TicTacToeGame");
                gameFactory = new TicTacToeGameFactory();
                break;
            case "TaquinGame":
                LOGGER.debug("Set TaquinGame");
                gameFactory = new TaquinGameFactory();
                break;
            case "ConnectFourGame":
                LOGGER.debug("Set ConnectFourGame");
                gameFactory = new ConnectFourGameFactory();
                break;
            default:
                LOGGER.error("Couldn't load a game from the save");
                gameFactory = null;
        }
        try {
            return gameFactory.createGame(
                    save.getBoardSize(),
                    save.getPlayers().stream().toList(),
                    TokenPositionFactory.from(save.getBoardTokens()),
                    TokenPositionFactory.from(save.getRemovedTokens())
            );
        }catch (InconsistentGameDefinitionException definitionException){
            LOGGER.error(definitionException.toString());
        }
        return null;
    }
}
