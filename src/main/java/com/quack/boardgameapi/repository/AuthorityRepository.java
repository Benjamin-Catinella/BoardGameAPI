package com.quack.boardgameapi.repository;

import com.quack.boardgameapi.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
    AuthorityEntity getByRole(String role);
}
