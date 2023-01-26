package com.quack.boardgameapi.gamedata;
public record GameCreationParams(
        String gameType,
        int numberOfPlayers,
        int boardSize
) implements CreationParams {}
