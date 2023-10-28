package com.example.databaseapp.dbLogick;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionService {

    static {
        registerJdbcDriver();
    }
    private static void registerJdbcDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't register JDBC driver", e);
        }
    }

    public Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/public_utilities",
                    "postgres",
                    "0000"
            );
        } catch (SQLException exception) {
            throw new RuntimeException("Couldn't open SQL connection", exception);
        }
    }
}
