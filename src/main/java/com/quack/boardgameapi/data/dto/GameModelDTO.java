package com.quack.boardgameapi.data.dto;

import com.quack.boardgameapi.data.interfaces.DTO;

public record GameModelDTO(
        int id,
        String gameType
) implements DTO {

}
