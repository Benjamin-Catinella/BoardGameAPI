package com.quack.boardgameapi.service.persistence;

import com.quack.boardgameapi.entity.GameSaveEntity;
import com.quack.boardgameapi.repository.GameSaveRepository;
import com.quack.boardgameapi.service.interfaces.GamePersistenceEngine;
import com.quack.boardgameapi.service.interfaces.GameSavePersistenceEngine;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;

public class GameSaveJPAPersistenceEngine implements GameSavePersistenceEngine {

    private GameSaveRepository gameSaveRepository;

    @Override
    public GameSaveEntity create(GameSaveEntity gameSaveEntity) {
        return gameSaveRepository.save(gameSaveEntity);
    }

    @Override
    public GameSaveEntity read(Object id) {
        return gameSaveRepository.getById((Long)id);
    }

    @Override
    public GameSaveEntity update(GameSaveEntity gameSaveEntity, GameSaveEntity t2) {
        return null;
    }

    @Override
    public GameSaveEntity delete(GameSaveEntity gameSaveEntity) {
        return null;
    }

    @Override
    public Collection<GameSaveEntity> getAll() {
        return null;
    }
}
