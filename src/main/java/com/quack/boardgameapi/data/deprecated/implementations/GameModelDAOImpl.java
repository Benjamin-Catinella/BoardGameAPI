package com.quack.boardgameapi.data.deprecated.implementations;

import com.quack.boardgameapi.data.Database;
import com.quack.boardgameapi.data.deprecated.dto.GameModelDTO;
import com.quack.boardgameapi.data.deprecated.interfaces.GameModelDAO;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameModelDAOImpl implements GameModelDAO {
    @Override
    public List<GameModelDTO> getAll() {
        String query = "SELECT * FROM game_models";
        ResultSet resultSet = null;
        List<GameModelDTO> gameModels = new ArrayList<>();
        try {
            resultSet = Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                GameModelDTO gameModel = new GameModelDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                gameModels.add(gameModel);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
        return gameModels;
    }

    @Override
    public GameModelDTO select(int id) throws SQLException {
        String query = "SELECT * FROM game_models WHERE id = " + id;
        ResultSet resultSet = null;
        GameModelDTO gameModel = null;
        try {
            resultSet = Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery(query);

            if (resultSet.next()) {
                gameModel = new GameModelDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
        return gameModel;
    }

    @Override
    public int update(GameModelDTO gameModelDTO) throws SQLException {
        return 0;
    }

    @Override
    public int insert(GameModelDTO gameModelDTO) throws SQLException {

        String query =
                "INSERT INTO game_models(name) " +
                        "VALUES (?)";
        try {
            PreparedStatement statement;
            statement = Database.getInstance()
                    .getConnection()
                    .prepareStatement(query);
            statement.setString(1, gameModelDTO.gameType());
            statement.executeUpdate();
            return 1;
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            return 0;
        }
    }

    @Override
    public int delete(GameModelDTO gameModelDTO) throws SQLException {
        return 0;
    }
}
