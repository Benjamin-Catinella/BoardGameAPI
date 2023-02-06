package com.quack.boardgameapi.data.dto;

import com.quack.boardgameapi.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public record UserDTO(UUID uuid, String username) {
    public static UserDTO from(UserEntity entity){
        return new UserDTO(entity.getUuid(), entity.getUsername());
    }

    public static Collection<UserDTO> from(Collection<UserEntity> allUsers) {
        Collection<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity user : allUsers){
            userDTOS.add(from(user));
        }
        return userDTOS;
    }
}
