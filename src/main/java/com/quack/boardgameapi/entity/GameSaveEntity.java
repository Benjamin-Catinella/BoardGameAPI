package com.quack.boardgameapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class GameSaveEntity implements DataEntity {
    @Id
    private Long id;
    @OneToMany
    private Collection<TokenPositionEntity> boardTokens;
    @OneToMany
    private Collection<TokenPositionEntity> removedTokens;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public Collection<TokenPositionEntity> getBoardTokens() {
        return boardTokens;
    }

    public void setBoardTokens(Collection<TokenPositionEntity> boardTokens) {
        this.boardTokens = boardTokens;
    }

    public Collection<TokenPositionEntity> getRemovedTokens() {
        return removedTokens;
    }

    public void setRemovedTokens(Collection<TokenPositionEntity> removedTokens) {
        this.removedTokens = removedTokens;
    }
}
