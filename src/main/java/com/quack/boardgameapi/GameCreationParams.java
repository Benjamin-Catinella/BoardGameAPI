package com.quack.boardgameapi;

public record GameCreationParams(
        String gameName,
        int difficultyId
) {}
