package com.quack.boardgameapi.data.deprecated.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T select(int id) throws SQLException;
    int update(T t) throws SQLException;
    int insert(T t) throws SQLException;
    int delete(T t) throws SQLException;
}
