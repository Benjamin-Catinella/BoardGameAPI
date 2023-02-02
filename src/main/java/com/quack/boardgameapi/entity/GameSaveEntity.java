package com.quack.boardgameapi.entity;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class GameSaveEntity implements DataEntity {
    @Id
    private Long id;

    private String gameName;

    private int boardSize;

    @OneToMany
    private Collection<TokenPositionEntity> boardTokens;
    @OneToMany
    private Collection<TokenPositionEntity> removedTokens;
    @OneToMany
    private Collection<UserEntity> players;

    public Long getId() {
        return id;
    }

    public GameSaveEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGameName() {
        return gameName;
    }

    public GameSaveEntity setGameName(String gameName) {
        this.gameName = gameName;
        return this;
    }

    public Collection<TokenPositionEntity> getBoardTokens() {
        return boardTokens;
    }

    public GameSaveEntity setBoardTokens(Collection<TokenPositionEntity> boardTokens) {
        this.boardTokens = boardTokens;
        return this;
    }

    public Collection<TokenPositionEntity> getRemovedTokens() {
        return removedTokens;
    }

    public GameSaveEntity setRemovedTokens(Collection<TokenPositionEntity> removedTokens) {
        this.removedTokens = removedTokens;
        return this;
    }

    public Collection<UserEntity> getPlayers() {
        return players;
    }

    public GameSaveEntity setPlayers(Collection<UserEntity> players) {
        this.players = players;
        return this;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public GameSaveEntity setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        return this;
    }

    @Override
    public String toString(){
        List<String> iterable = new ArrayList<>();
        iterable.add(id.toString());
        iterable.add(gameName);
        return Strings.join(iterable, '|');
    }
}
