package com.quack.boardgameapi.Controller;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartBeat implements HeartBeatSensor{
    Random random = new Random();
    @Override
    public int get() {
        return random.nextInt(40,230);
    }

}
