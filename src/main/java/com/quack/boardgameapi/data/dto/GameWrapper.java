package com.quack.boardgameapi.data.dto;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;

public record GameWrapper(Game game, UUID uuid) {
}
