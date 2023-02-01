package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.GameSaveEntity;
import fr.le_campus_numerique.square_games.engine.Game;

public class GameSaveEntityFactory {
    public static GameSaveEntity from(Game game){
        GameSaveEntity save;
        save = new GameSaveEntity();
        save.setBoardTokens(TokenPositionEntityFactory.from(game.getRemainingTokens()));
        save.setRemovedTokens(TokenPositionEntityFactory.from(game.getRemovedTokens()));
        return save;
    }
}
