package com.quack.boardgameapi.Data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {

    private static Database instance;

    public static Database getInstance() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    private Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:6603/BoardGameDatabase", "root", "helloworld");
        }
        return connection;
    }
}
