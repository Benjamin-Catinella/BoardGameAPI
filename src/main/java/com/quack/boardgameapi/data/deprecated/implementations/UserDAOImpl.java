package com.quack.boardgameapi.data.deprecated.implementations;

import com.quack.boardgameapi.data.Database;
import com.quack.boardgameapi.data.deprecated.dto.UserDTO;
import com.quack.boardgameapi.data.deprecated.interfaces.UserDAO;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public List<UserDTO> getAll() {
        String query = "SELECT * FROM users";
        ResultSet resultSet = null;
        List<UserDTO> users = new ArrayList<>();
        try {
            resultSet = Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                UserDTO user = new UserDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("birthday"),
                        resultSet.getInt("walletId")
                );
                users.add(user);
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
        return users;
    }

    @Override
    public UserDTO select(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = " + id;
        ResultSet resultSet = null;
        UserDTO user = null;
        try {
            resultSet = Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery(query);

            if (resultSet.next()) {
                user = new UserDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("birthday"),
                        resultSet.getInt("walletId")
                );
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
        return user;
    }

    /**
     * Inserts a user in the database
     *
     * @param user the user to insert
     * @return 1 if succeeds, 0 if failed
     * @throws SQLException
     */
    @Override
    public int insert(UserDTO user) throws SQLException {
        String query =
                "INSERT INTO users(username,birthday,password,walletId) " +
                        "VALUES (?,?,?,?)";
        try {
            PreparedStatement statement;
            statement = Database.getInstance()
                    .getConnection()
                    .prepareStatement(query);
            statement.setString(1, user.username());
            statement.setTimestamp(2, user.birthday());
            statement.setString(3, user.password());
            statement.setInt(4, user.walletId());
            statement.executeUpdate();
            return 1;
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            return 0;
        }
    }


    @Override
    public int update(UserDTO user) throws SQLException {
        String query =
                "UPDATE users(username,birthday,password,walletId) " +
                        "VALUES (?,?,?,?)" +
                        "WHERE user.id = " + user.id();
        try {
            PreparedStatement statement;
            statement = Database.getInstance()
                    .getConnection()
                    .prepareStatement(query);
            statement.setString(1, user.username());
            statement.setTimestamp(2, user.birthday());
            statement.setString(3, user.password());
            statement.setInt(4, user.walletId());
            statement.executeUpdate();
            return 1;
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            return 0;
        }
    }

    @Override
    public int delete(UserDTO user) throws SQLException {
        String query =
                "DELETE FROM users " +
                        "WHERE id = " + user.id();
        try {
            Database.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeUpdate(query);
            return 1;
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            return 0;
        }
    }
}
