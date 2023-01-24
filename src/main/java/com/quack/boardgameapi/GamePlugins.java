package com.quack.boardgameapi;

import com.quack.boardgameapi.plugins.Plugin;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GamePlugins {
    private final List<Plugin> collection;

    public GamePlugins(final List<Plugin> collection) {
        this.collection = collection;
    }

    private final Map<String,Plugin> plugins = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        this.plugins.putAll(this.collection.stream().collect(Collectors.toUnmodifiableMap(Plugin::gameId, Function.identity())));
    }

    public List<Plugin> getPlugins(){
        return Collections.unmodifiableList(this.collection);
    }
    public Plugin getPluginByGameId(String gameId){
        return this.plugins.get(gameId);
    }
}
