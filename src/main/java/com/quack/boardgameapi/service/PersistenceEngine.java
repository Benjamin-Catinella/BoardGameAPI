package com.quack.boardgameapi.service;

import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Used to define ways of manipulating persisting data of type < T >
 * @param <T>
 */
@Service
public interface PersistenceEngine<T> {
    Object create(T t);
    T read(String s);
    Object update(T t, T t2);
    Object delete(T t);
    Collection<T> getAll();
    String getId();
}
