package com.quack.boardgameapi.service;

import com.quack.boardgameapi.service.interfaces.UserStatsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@PropertySource("/application.properties")
public class UserStatsServiceImpl implements UserStatsService {

    @Value("${api.url}/api/users")
    private String usersURL;

    private UserStatsServiceImpl(){
    }

    @Override
    public int getUserWins(UUID uuid) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s/wins",usersURL,uuid);
        try {
            return restTemplate.getForObject(url, int.class);
        }
        catch (NullPointerException nullPointerException){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public int getUserLoses(UUID uuid) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/%s/loses",usersURL,uuid);
        try {
            return restTemplate.getForObject(url, int.class);
        }
        catch (NullPointerException nullPointerException){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
