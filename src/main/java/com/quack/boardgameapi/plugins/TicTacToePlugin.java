package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.gamedata.CreationParams;
import com.quack.boardgameapi.gamedata.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class TicTacToePlugin implements GamePlugin {

    @Value("${TicTacToe.defaultPlayerCount}")
    int defaultPlayerCount;
    @Value("${TicTacToe.defaultBoardSize}")
    int defaultBoardSize;
    @Value("${TicTacToe.gameId}")
    String gameId;

    private TicTacToeGameFactory ticTacToeGameFactory;
    public TicTacToePlugin(){
        ticTacToeGameFactory = new TicTacToeGameFactory();
    }
    @Override
    public CreationParams getDefaultParams() {
        return new GameCreationParams(gameId, defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public GameFactory getFactoryInstance() {
        return this.ticTacToeGameFactory;
    }
    @Override
    public String gameId() {
        return this.gameId;
    }
}
