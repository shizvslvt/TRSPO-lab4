package com.example.trspolab4.classes;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;

    public Database() {
        try {
            String databaseUser = "root";
            String databasePassword = "";
            String databaseName = "realtor_office";
            String url = "jdbc:mysql://localhost/" + databaseName;
            connection = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Connection getConnection() {
        return connection;
    }
}
