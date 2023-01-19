package com.quack.boardgameapi;

import org.springframework.stereotype.Service;

@Service
public class OneHeartBeat implements HeartBeatSensor{
    @Override
    public int get() {
        return 1;
    }
}
