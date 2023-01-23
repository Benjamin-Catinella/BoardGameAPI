package com.quack.boardgameapi;

public record GameModelCreationParams(
        String gameName

) implements CreationParams {}
