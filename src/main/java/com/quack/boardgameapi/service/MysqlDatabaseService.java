package com.quack.boardgameapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Profile("mysql")
public class MysqlDatabaseService {

    private DataSource dataSource;

    @Autowired
    public MysqlDatabaseService(){
        createDataSource();
    }

    public DataSource getDataSource(){
        return this.dataSource;
    }

    private void createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(" com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("helloworld");
        dataSource.setUrl("jdbc:mysql://localhost:6603/GameEngineDatabase");

        this.dataSource = dataSource;
    }
}
