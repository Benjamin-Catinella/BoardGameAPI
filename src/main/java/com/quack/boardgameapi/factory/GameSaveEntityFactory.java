package com.quack.boardgameapi.factory;

import com.quack.boardgameapi.entity.GameSaveEntity;
import fr.le_campus_numerique.square_games.engine.Game;

import javax.validation.constraints.NotNull;

public class GameSaveEntityFactory {
    public static GameSaveEntity from(@NotNull Game game) {
        GameSaveEntity save;
        save = new GameSaveEntity();
        save.setGameName(game.getClass().getName());
        save.setBoardTokens(TokenPositionEntityFactory.from(game.getRemainingTokens()));
        save.setRemovedTokens(TokenPositionEntityFactory.from(game.getRemovedTokens()));
        save.setPlayers(UserEntityFactory.from(game.getPlayerIds()));
        save.setBoardSize(game.getBoardSize());
        return save;
    }
}
