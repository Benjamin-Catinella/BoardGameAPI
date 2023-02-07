package com.quack.boardgameapi.service.interfaces;

import java.util.UUID;

public interface UserStatsService {

    int getUserWins(UUID uuid);
    int getUserLoses(UUID uuid);

}
