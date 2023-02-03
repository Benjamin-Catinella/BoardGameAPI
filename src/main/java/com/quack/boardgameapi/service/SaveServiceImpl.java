package com.quack.boardgameapi.service;

import com.quack.boardgameapi.entity.GameSaveEntity;
import com.quack.boardgameapi.factory.GameFactory;
import com.quack.boardgameapi.factory.GameSaveEntityFactory;
import com.quack.boardgameapi.repository.GameSaveRepository;
import com.quack.boardgameapi.service.interfaces.GamePersistenceEngine;
import com.quack.boardgameapi.service.interfaces.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class SaveServiceImpl implements SaveService {
    public Logger LOGGER = LoggerFactory.getLogger(SaveServiceImpl.class);
    private GameSaveRepository gameSaveRepository;

    @Autowired
    private GamePersistenceEngine gamePersistenceEngine;

    public SaveServiceImpl(GameSaveRepository gameSaveRepository) {
        this.gameSaveRepository = gameSaveRepository;
    }

    @Override
    public Long saveGameFromGameUUID(UUID uuid) {
        LOGGER.debug("Getting game with uuid : " + uuid);
        Game game = gamePersistenceEngine.read(uuid.toString());
        LOGGER.debug("Saving game with uuid : " + uuid);
        return gameSaveRepository.save(GameSaveEntityFactory.from(game)).getId();
    }

    @Override
    public Long saveGame(Game game) {
        LOGGER.debug("Saving game " + game);
        GameSaveEntity save = GameSaveEntityFactory.from(game);
        LOGGER.debug("Save : " + save);

        Long id = gameSaveRepository.save(save).getId();
        LOGGER.debug("Id : " + id);
        return id;
    }

    @Override
    public GameSaveEntity loadSave(Long id) {
        GameSaveEntity save = gameSaveRepository.getById(id);
        return save;
    }

    @Override
    public Game loadGameFromSaveId(Long id) {
        LOGGER.debug("Loading game from save id :" + id);
        Game game = GameFactory.from(loadSave(id));
        LOGGER.debug("Loaded game | " + game);
        return game;
    }

    @Override
    public Collection<GameSaveEntity> getAllSaves() {
        return (Collection<GameSaveEntity>) gameSaveRepository.findAll();
    }


}
