///*
//package com.quack.boardgameapi.service;
//
//import com.quack.boardgameapi.service.interfaces.PersistenceEngine;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//*/
///**
// * <b>DEPRECATED</b>
// * Defines which Persistence engine to use according to pre-constructed map and properties
// *
// * @param <T>
// *//*
//
//@Service
//@Deprecated
//@PropertySource("application.properties")
//public class PersistenceEngineMap<T> {
//    @Value("${application.persistence.mode}")
//    private String persistenceMode;
//    private final List<PersistenceEngine<T>> persistenceEngineCollection;
//    private final Map<String, PersistenceEngine<T>> persistenceEngineMap = new HashMap<>();
//    @Autowired
//    public PersistenceEngineMap(final List<PersistenceEngine<T>> collection) {
//        this.persistenceEngineCollection = collection;
//    }
//    @PostConstruct
//    public void postConstruct() {
//        this.persistenceEngineMap.putAll(this.persistenceEngineCollection.stream().collect(Collectors.toUnmodifiableMap(PersistenceEngine::getId, Function.identity())));
//        System.out.println(this.persistenceEngineMap);
//    }
//    public List<PersistenceEngine<T>> getPersistenceEngineMap(){
//        return Collections.unmodifiableList(this.persistenceEngineCollection);
//    }
//    public PersistenceEngine<T> getPersistenceById(String id){
//        return this.persistenceEngineMap.get(id);
//    }
//    public PersistenceEngine<T> getPersistenceEngine(){
//        return this.persistenceEngineMap.get(persistenceMode);
//    }
//}
//*/
