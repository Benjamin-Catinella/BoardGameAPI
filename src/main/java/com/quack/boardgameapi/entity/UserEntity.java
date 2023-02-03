package com.quack.boardgameapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    private UUID uuid;
    private @NotNull String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<GameSaveEntity> saves;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public Collection<GameSaveEntity> getSaves() {
        return saves;
    }

    public void setSaves(Collection<GameSaveEntity> saves) {
        this.saves = saves;
    }
}
