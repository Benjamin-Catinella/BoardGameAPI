package com.quack.boardgameapi.service.persistence;

import com.quack.boardgameapi.service.interfaces.GamePersistenceEngine;
import fr.le_campus_numerique.square_games.engine.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameLocalPersistenceEngine implements GamePersistenceEngine {
    private static Logger LOGGER = LoggerFactory.getLogger(GameLocalPersistenceEngine.class);
    private HashMap<String, Game> collection = new HashMap<>();
    /**
     * Adds a game to the collection and returns the uuid it was assigned
     * @param game
     * @return
     */
    public Game create(Game game){
        String uuid = UUID.randomUUID().toString();
        this.collection.put(uuid,game);
        LOGGER.debug("Added " + game.toString() +" to collection");
        return game;
    }
    @Override
    public Game read(Object uuid){
        LOGGER.debug("Getting " + uuid.toString() + " from collection");
        Game game = this.collection.get(uuid);
        LOGGER.debug("Got " + game.toString() +" from collection");
        return game;
    }
    @Override
    public Game update(Game game, Game t2) {
        return null;
    }
    @Override
    public Game delete(Game game) {
        return null;
    }
    @Override
    public Collection<Game> getAll() {
        return this.collection.values();
    }

    @Override
    public Collection<String> getGamesUUIDs() {
        return this.collection.keySet();
    }
}
