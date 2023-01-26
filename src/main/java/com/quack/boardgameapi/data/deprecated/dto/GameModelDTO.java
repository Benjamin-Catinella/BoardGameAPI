package com.quack.boardgameapi.data.deprecated.dto;

import com.quack.boardgameapi.data.deprecated.interfaces.DTO;

public record GameModelDTO(
        int id,
        String gameType
) implements DTO {

}
