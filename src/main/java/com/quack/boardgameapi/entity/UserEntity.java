package com.quack.boardgameapi.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Entity
public class UserEntity implements UserDetails {

    @Id
    private UUID uuid;
    private @NotNull String username;
    private @NotNull String password;
    private @NotNull String email;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Collection<AuthorityEntity> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<GameSaveEntity> saves;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<TokenPositionEntity> tokens;

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public Collection<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public UserEntity setAuthorities(Collection<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }

    public Collection<GameSaveEntity> getSaves() {
        return saves;
    }

    public UserEntity setSaves(Collection<GameSaveEntity> saves) {
        this.saves = saves;
        return this;
    }

    public Collection<TokenPositionEntity> getTokens() {
        return tokens;
    }

    public UserEntity setTokens(Collection<TokenPositionEntity> tokens) {
        this.tokens = tokens;
        return this;
    }
}
