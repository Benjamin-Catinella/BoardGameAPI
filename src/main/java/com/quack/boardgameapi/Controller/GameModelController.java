package com.quack.boardgameapi.Controller;

import com.quack.boardgameapi.Data.Interfaces.DTO;
import com.quack.boardgameapi.Data.Interfaces.GameModelDAO;
import com.quack.boardgameapi.Data.Implementations.GameModelDAOImpl;
import com.quack.boardgameapi.Data.DTO.GameModelDTO;
import com.quack.boardgameapi.GameModelCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
public class GameModelController implements Controller<GameModelDTO> {

    @Autowired
    GameModelDAO gameModelDAO = new GameModelDAOImpl();


    @PostMapping("/games")
    public int createGameModel(@RequestBody GameModelCreationParams gameCreationParams){
        try {
            GameModelDTO gameModelDTO = new GameModelDTO(0, gameCreationParams.gameName());
            gameModelDAO.insert(gameModelDTO);
            return 1;
        }catch (Exception e){
            System.err.println(e);
            return 0;
        }
    }

    @GetMapping("/games")
    public List<GameModelDTO> getAll(){
        GameModelDAO gameModelDAO = new GameModelDAOImpl();
        try {
            return gameModelDAO.getAll();
        }catch (Exception e){
            System.err.println(e);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    @GetMapping("/games/{gameId}")
    public GameModelDTO get(@PathVariable String gameId){
        GameModelDAO gameModelDAO = new GameModelDAOImpl();
        try {
            DTO dto = gameModelDAO.select(Integer.parseInt(gameId));
            if(dto != null){
                return (GameModelDTO) dto;
            }else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                );
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return null;
    }

}
