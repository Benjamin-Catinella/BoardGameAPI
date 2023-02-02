package com.quack.boardgameapi.entity;

import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Entity
public class TokenPositionEntity implements DataEntity {
    @Id
    private Long id;
    @ManyToOne
    private UserEntity owner;
    @NotNull
    private String tokenName;
    private int x;
    private int y;

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
