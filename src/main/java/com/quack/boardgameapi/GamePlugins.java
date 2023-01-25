package com.quack.boardgameapi;

import com.quack.boardgameapi.plugins.Plugin;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GamePlugins {
    private final List<Plugin> pluginCollection;
    public GamePlugins(final List<Plugin> collection) {
        this.pluginCollection = collection;
    }
    private final Map<String,Plugin> pluginMap = new HashMap<>();
    @PostConstruct
    public void postConstruct() {
        this.pluginMap.putAll(this.pluginCollection.stream().collect(Collectors.toUnmodifiableMap(Plugin::gameId, Function.identity())));
    }
    public List<Plugin> getPluginMap(){
        return Collections.unmodifiableList(this.pluginCollection);
    }
    public Plugin getPluginByGameId(String gameId){
        return this.pluginMap.get(gameId);
    }
}
