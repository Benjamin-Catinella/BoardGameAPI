package com.quack.boardgameapi;
public record GameCreationParams(
        String gameType,
        int numberOfPlayers,
        int boardSize
) implements CreationParams {}
