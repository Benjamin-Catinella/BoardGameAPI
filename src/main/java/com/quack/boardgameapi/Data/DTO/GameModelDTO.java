package com.quack.boardgameapi.Data.DTO;

import com.quack.boardgameapi.Data.Interfaces.DTO;

public record GameModelDTO(
        int id,
        String gameType
) implements DTO {

}
