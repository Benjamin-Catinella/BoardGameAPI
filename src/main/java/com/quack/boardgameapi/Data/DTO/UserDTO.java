package com.quack.boardgameapi.Data.DTO;

import com.quack.boardgameapi.Data.Interfaces.DTO;

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
