package com.quack.boardgameapi.plugins;

import com.quack.boardgameapi.CreationParams;
import fr.le_campus_numerique.square_games.engine.GameFactory;

public interface Plugin {
    CreationParams getDefaultParams();

    GameFactory getFactory();
    String gameId();
}
