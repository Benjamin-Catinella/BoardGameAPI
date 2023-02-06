package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.entity.GameSaveEntity;
import com.quack.boardgameapi.service.SaveService;
import com.quack.boardgameapi.service.SaveServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class SaveController {
    private SaveService saveService;

    @Autowired
    public SaveController(SaveService saveService) {
        this.saveService = saveService;
    }

    @GetMapping("/games/saves")
    public Collection<GameSaveEntity> getAllSaves(){
        return saveService.getAllSaves();
    }

    @PutMapping("games/saves/{uuid}")
    public Long saveGameFromGameUUID(@PathVariable("uuid") String uuid){
        return saveService.saveGameFromGameUUID(UUID.fromString(uuid));
    }

    @PostMapping("/games/saves")
    /**
     * Load game into memory and returns its id
     * Requests the service layer to load a game from its ID
     *
     * @param saveID The Long ID of the save
     * @return
     */
    public Game loadGameFromSave(@RequestBody Long saveID) {
        return saveService.loadGameFromSaveId(saveID);
    }
}
