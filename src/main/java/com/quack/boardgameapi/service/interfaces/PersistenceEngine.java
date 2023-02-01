package com.quack.boardgameapi.service.interfaces;

import java.util.Collection;

public interface PersistenceEngine<T> {
    T create(T t);
    T read(Object s);
    T update(T t, T t2);
    T delete(T t);
    Collection<T> getAll();
}
