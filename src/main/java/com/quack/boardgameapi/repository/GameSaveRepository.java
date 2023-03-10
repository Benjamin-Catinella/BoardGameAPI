package com.quack.boardgameapi.repository;

import com.quack.boardgameapi.entity.GameSaveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GameSaveRepository extends CrudRepository<GameSaveEntity, Long> {
    GameSaveEntity getById(Long id);
}
