package com.quack.boardgameapi.data.dto;

import com.quack.boardgameapi.data.interfaces.DTO;

import java.sql.Timestamp;
public record PlayedMatchDTO (int id, int gameId, int difficultyId, Timestamp datePlayed) implements DTO {}
