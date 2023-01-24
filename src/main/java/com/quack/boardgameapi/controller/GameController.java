package com.quack.boardgameapi.controller;

import com.quack.boardgameapi.GameCollection;
import com.quack.boardgameapi.GameCreationParams;
import com.quack.boardgameapi.GamePlugins;
import com.quack.boardgameapi.plugins.Plugin;
import com.quack.boardgameapi.plugins.TicTacToePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.apache.tomcat.util.json.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class GameController {
    /*
    Demander pour le plugin et l'utilité de mettre tous les autowire
     */
    @Autowired
    GameCollection gameCollection;

    @Autowired
    GamePlugins plugins;

    @GetMapping("/games")
    public Object getGames(){
        return gameCollection.getCollection();
    }
    @GetMapping("/games/{uuid}/tokens/")
    public Token getTokens(){
        return null;
    }
    @PostMapping("/games/{uuid}/tokens/")
    public boolean moveToken(){
        return false;
    }

    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams gameCreationParams){

        String gameType = gameCreationParams.gameType();

        Game game = plugins.getPluginByGameId(gameType)
                            .getFactory()
                            .createGame(
                                    gameCreationParams.numberOfPlayers(),
                                    gameCreationParams.boardSize()
                            );
        String gameUUID = gameCollection.addGame(game);
        return gameUUID;
    }

}
