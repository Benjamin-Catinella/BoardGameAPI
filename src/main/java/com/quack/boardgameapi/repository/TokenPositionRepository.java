package com.quack.boardgameapi.repository;

import com.quack.boardgameapi.entity.TokenPositionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TokenPositionRepository extends CrudRepository<TokenPositionEntity,Long> {
}
