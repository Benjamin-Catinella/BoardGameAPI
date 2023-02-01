package com.quack.boardgameapi.service.persistence;

import com.quack.boardgameapi.service.interfaces.GamePersistenceEngine;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@Profile("mysql")
public class GameMYSQLPersistenceEngine implements GamePersistenceEngine{
    @Override
    public Game create(Game game) {
        return null;
    }

    @Override
    public Game read(Object s) {
        return null;
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
        return null;
    }
}
