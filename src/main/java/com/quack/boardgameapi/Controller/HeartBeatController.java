package com.quack.boardgameapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HeartBeatController{
    @Autowired
    private HeartBeatSensor heartBeatSensor;
    @GetMapping("/heartbeat/{number}")
    public int[] getHeartbeats(@PathVariable("number") int n, @PathVariable("type") String type){
        int[] heartbeats = new int[n];
        for (int i = 0; i < n; i++) {
            heartbeats[i] = heartBeatSensor.get();
        }
        return heartbeats;
    }
    @GetMapping("/heartbeat")
    public int getHeartbeat(){
        return heartBeatSensor.get();
    }


}
