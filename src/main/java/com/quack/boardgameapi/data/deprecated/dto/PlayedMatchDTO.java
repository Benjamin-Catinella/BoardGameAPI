package com.quack.boardgameapi.data.deprecated.dto;

import com.quack.boardgameapi.data.deprecated.interfaces.DTO;

import java.sql.Timestamp;
public record PlayedMatchDTO (int id, int gameId, int difficultyId, Timestamp datePlayed) implements DTO {}
