package com.quack.boardgameapi.service;

import com.quack.boardgameapi.entity.GameSaveEntity;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.UUID;

public interface SaveService {
    Long saveGameFromGameUUID(UUID uuid);

    Long saveGame(Game game);

    GameSaveEntity loadSave(Long id);

    Game loadGameFromSaveId(Long id);

    Collection<GameSaveEntity> getAllSaves();
}
