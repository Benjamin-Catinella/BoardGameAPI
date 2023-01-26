package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.plugins.GamePlugin;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GamePluginsMap {
    private final List<GamePlugin> pluginCollection;
    public GamePluginsMap(final List<GamePlugin> collection) {
        this.pluginCollection = collection;
    }
    private final Map<String, GamePlugin> pluginMap = new HashMap<>();
    @PostConstruct
    public void postConstruct() {
        this.pluginMap.putAll(this.pluginCollection.stream().collect(Collectors.toUnmodifiableMap(GamePlugin::gameId, Function.identity())));
    }
    public List<GamePlugin> getPluginMap(){
        return Collections.unmodifiableList(this.pluginCollection);
    }
    public GamePlugin getPluginByGameId(String gameId){
        return this.pluginMap.get(gameId);
    }
}
