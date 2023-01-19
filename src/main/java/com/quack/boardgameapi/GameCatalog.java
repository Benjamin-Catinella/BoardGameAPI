package com.quack.boardgameapi;

import java.util.Collection;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();
}
