package com.quack.boardgameapi.gamedata;

import com.quack.boardgameapi.service.PersistenceEngine;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameLocalPersistenceEngine extends LocalPersistenceEngineData implements PersistenceEngine<Game>{
    private HashMap<String, Game> collection = new HashMap<>();
    /**
     * Adds a game to the collection and returns the uuid it was assigned
     * @param game
     * @return
     */
    @Override
    public String create(Game game){
        String uuid = UUID.randomUUID().toString();
        this.collection.put(uuid,game);
        return uuid;
    }
    @Override
    public Game read(String uuid){
        return this.collection.get(uuid);
    }
    @Override
    public Object update(Game game, Game t2) {
        return null;
    }
    @Override
    public Object delete(Game game) {
        return null;
    }
    @Override
    public Collection<Game> getAll() {
        return this.collection.values();
    }

    @Override
    public String getId() {
        return this.id;
    }
}
