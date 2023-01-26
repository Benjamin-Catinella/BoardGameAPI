package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.gamedata.CreationParams;
import com.quack.boardgameapi.gamedata.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class TaquinPlugin implements GamePlugin {
    @Value("${Taquin.defaultPlayerCount}")
    private int defaultPlayerCount;
    @Value("${Taquin.defaultBoardSize}")
    private int defaultBoardSize;
    @Value("${Taquin.gameId}")
    private String gameId;

    private TaquinGameFactory taquinGameFactory;
    public TaquinPlugin(){
        taquinGameFactory = new TaquinGameFactory();
    }
    @Override
    public CreationParams getDefaultParams() {
        return new GameCreationParams(gameId, defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public GameFactory getFactoryInstance() {
        return this.taquinGameFactory;
    }

    @Override
    public String gameId() {
        return gameId;
    }
}
