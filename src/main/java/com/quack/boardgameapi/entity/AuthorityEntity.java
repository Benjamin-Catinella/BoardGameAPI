package com.quack.boardgameapi.entity;

import com.quack.boardgameapi.security.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Entity
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String role;

    @ManyToOne
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public AuthorityEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AuthorityEntity setRole(String role) {
        this.role = role;
        return this;
    }

    public AuthorityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
