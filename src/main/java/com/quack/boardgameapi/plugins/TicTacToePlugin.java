package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.CreationParams;
import com.quack.boardgameapi.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class TicTacToePlugin implements Plugin{

    @Value("TicTacToe.defaultPlayerCount")
    int defaultPlayerCount;
    @Value("TicTacToe.defaultBoardSize")
    int defaultBoardSize;
    @Value("TicTacToe.gameId")
    String gameId;

    GameFactory gameFactory = new TicTacToeGameFactory();
    @Override
    public CreationParams getDefaultParams() {
        return new GameCreationParams("TicTacToe", defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public GameFactory getFactory() {
        return this.gameFactory;
    }
    @Override
    public String gameId() {
        return this.gameId;
    }
}
