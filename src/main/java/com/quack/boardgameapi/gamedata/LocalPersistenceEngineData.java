package com.quack.boardgameapi.gamedata;

public abstract class LocalPersistenceEngineData {

    @Deprecated
    /**
     * @serialField
     * Use @Profile
     */
    protected String id = "local";
}
