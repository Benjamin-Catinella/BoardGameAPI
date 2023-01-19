package com.quack.boardgameapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HeartBeatController{
    @Qualifier("randomHeartBeat")
    @Autowired
    private HeartBeatSensor heartBeatSensor;

    @GetMapping("/heartbeat/{number}")
    public int[] getHeartbeats(@PathVariable("number") int n){
        int[] heartbeats = new int[n];
        for (int i = 0; i < n; i++) {
            heartbeats[i] = heartBeatSensor.get();
        }
        return heartbeats;
    }

}
