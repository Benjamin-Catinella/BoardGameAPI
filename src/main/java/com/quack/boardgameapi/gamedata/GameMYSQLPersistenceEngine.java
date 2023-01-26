package com.quack.boardgameapi.gamedata;

import com.quack.boardgameapi.service.PersistenceEngine;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class GameMYSQLPersistenceEngine extends MYSQLPersistenceEngineData implements PersistenceEngine<Game> {
    @Override
    public Object create(Game game) {
        return null;
    }

    @Override
    public Game read(String s) {
        return null;
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
        return null;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
