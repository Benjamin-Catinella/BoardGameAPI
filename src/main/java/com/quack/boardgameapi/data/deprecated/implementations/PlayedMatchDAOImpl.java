package com.quack.boardgameapi.data.deprecated.implementations;

import com.quack.boardgameapi.data.Database;
import com.quack.boardgameapi.data.deprecated.dto.PlayedMatchDTO;
import com.quack.boardgameapi.data.deprecated.interfaces.PlayedMatchDAO;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class PlayedMatchDAOImpl implements PlayedMatchDAO {
    @Override
    public List getAll() {
        String query = "SELECT * FROM played_matches";
        ResultSet resultSet = null;
        List<PlayedMatchDTO> playedMatches = new ArrayList<>();
        try {
            resultSet = Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                playedMatches.add(new PlayedMatchDTO(
                        resultSet.getInt("id"),
                        resultSet.getInt("gameId"),
                        resultSet.getInt("difficultyId"),
                        resultSet.getTimestamp("datePlayed")
                ));
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
        return playedMatches;
    }

    @Override
    public PlayedMatchDTO select(int id) throws SQLException {
        return null;
    }

    @Override
    public int update(PlayedMatchDTO playedMatchDTO) throws SQLException {
        return 0;
    }

    @Override
    public int insert(PlayedMatchDTO playedMatchDTO) throws SQLException {
        String query = "INSERT INTO played_matches(gameId, difficultyId, datePlayed) " +
                "VALUES(?,?,?);";
        try {
            PreparedStatement statement;
            statement = Database.getInstance()
                    .getConnection()
                    .prepareStatement(query);
            statement.setInt(1, playedMatchDTO.gameId());
            statement.setInt(2, playedMatchDTO.difficultyId());
            statement.setTimestamp(3, playedMatchDTO.datePlayed());
            statement.executeUpdate();
            return 1;
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            return 0;
        }
    }

    @Override
    public int delete(PlayedMatchDTO playedMatchDTO) throws SQLException {
        return 0;
    }


}
