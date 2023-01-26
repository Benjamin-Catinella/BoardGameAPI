package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.gamedata.CreationParams;
import com.quack.boardgameapi.gamedata.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class ConnectFourPlugin implements GamePlugin {
    @Value("${ConnectFour.defaultPlayerCount}")
    int defaultPlayerCount;
    @Value("${ConnectFour.defaultBoardSize}")
    int defaultBoardSize;
    @Value("${ConnectFour.gameId}")
    String gameId;

    ConnectFourGameFactory gameFactory;
    public ConnectFourPlugin(){
        this.gameFactory = new ConnectFourGameFactory();
    }
    @Override
    public CreationParams getDefaultParams() {
        return new GameCreationParams(gameId, defaultPlayerCount,defaultBoardSize);
    }

    @Override
    public GameFactory getFactoryInstance() {
        return this.gameFactory;
    }

    @Override
    public String gameId() {
        return gameId;
    }
}
