package com.quack.boardgameapi.Controller;

import com.quack.boardgameapi.Data.DTO.PlayedMatchDTO;
import com.quack.boardgameapi.Data.Interfaces.PlayedMatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PlayedMatchController implements Controller<PlayedMatchDTO> {
    @Autowired
    PlayedMatchDAO playedMatchDAO;

    @Override
    @GetMapping("/matches")
    public List<PlayedMatchDTO> getAll() {
        return playedMatchDAO.getAll();
    }

    @PostMapping("/matches")
    public int newMatch(@RequestBody PlayedMatchDTO playedMatchDTO){
        try {
            return playedMatchDAO.insert(playedMatchDTO);
        }catch (SQLException e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
