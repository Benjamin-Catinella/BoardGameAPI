package com.quack.boardgameapi.Data.DTO;

import com.quack.boardgameapi.Data.Interfaces.DTO;

import java.sql.Time;
import java.sql.Timestamp;
public record PlayedMatchDTO (int id, int gameId, int difficultyId, Timestamp datePlayed) implements DTO {}
