package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.CreationParams;
import com.quack.boardgameapi.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class TaquinPlugin implements Plugin {
    @Value("${Taquin.defaultPlayerCount}")
    int defaultPlayerCount;
    @Value("${Taquin.defaultBoardSize}")
    int defaultBoardSize;
    @Value("${Taquin.gameId}")
    String gameId;

    TaquinGameFactory taquinGameFactory;
    public TaquinPlugin(){
        taquinGameFactory = new TaquinGameFactory();
    }
    @Override
    public CreationParams getDefaultParams() {
        return new GameCreationParams(gameId, 1, 4);
    }

    @Override
    public GameFactory getFactoryInstance() {
        return taquinGameFactory;
    }

    @Override
    public String gameId() {
        return gameId;
    }
}
