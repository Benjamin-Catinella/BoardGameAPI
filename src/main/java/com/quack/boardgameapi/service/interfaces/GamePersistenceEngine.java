package com.quack.boardgameapi.service.interfaces;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Used to define ways of manipulating persisting data of type < T >
 * @param <T>
 */
@Service
public interface GamePersistenceEngine extends PersistenceEngine<Game> {
    Collection<String> getGamesUUIDs();
}
