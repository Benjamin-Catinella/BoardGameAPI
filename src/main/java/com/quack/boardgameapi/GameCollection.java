package com.quack.boardgameapi;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameCollection {
    private HashMap<String, Game> collection = new HashMap<>();

    /**
     * Adds a game to the collection and returns the uuid it was assigned
     * @param game
     * @return
     */
    public String addGame(Game game){
        String uuid = UUID.randomUUID().toString();
        this.collection.put(uuid,game);
        return uuid;
    }
    public Map<String, Game> getCollection() {
        return Collections.unmodifiableMap(this.collection);
    }
}
