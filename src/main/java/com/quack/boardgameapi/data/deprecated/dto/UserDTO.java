package com.quack.boardgameapi.data.deprecated.dto;

import com.quack.boardgameapi.data.deprecated.interfaces.DTO;

import java.sql.Timestamp;

public record UserDTO(int id, String username, String password, Timestamp birthday, int walletId) implements DTO {
    @Override
    public String toString() {
        return this.id + " | " +
                this.username + " | " +
                this.password + " | " +
                this.birthday + " | " +
                this.walletId;
    }
}
