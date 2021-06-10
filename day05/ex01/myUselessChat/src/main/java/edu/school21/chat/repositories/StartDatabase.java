package edu.school21.chat.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StartDatabase {
    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public StartDatabase(String DB_URL, String USER, String PASS) {
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    private java.sql.Connection getDBConnection() {
        java.sql.Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("world");
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    public Statement startDB()  {
        java.sql.Connection conn = getDBConnection();
        String data = null;
        String schema = null;
        try {
            data = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.sql")));
            schema = new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(schema);
//            statement.executeUpdate(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }
}
