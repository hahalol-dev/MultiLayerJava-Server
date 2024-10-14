package com.example.multifileserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    private Connection connect() throws Exception {
        String url = "jdbc:sqlite:test.db";
        return DriverManager.getConnection(url);
    }

    public List<String> handleQueries(String queryType, String userInput) throws Exception {
        List<String> results = new ArrayList<>();
        String query = "";

        switch (queryType) {
            case "vulnerable":
                // Vulnerable query - SQL Injection (due to direct user input in the query)
                query = "SELECT * FROM users WHERE username = '" + userInput + "'";
                break;
            case "safe-int":
                // Safe query - the external input is an integer
                query = "SELECT * FROM users WHERE id = " + Integer.parseInt(userInput);
                break;
            case "safe-constant":
                // Safe query - the value is constant
                String fixedUsername = "admin";
                query = "SELECT * FROM users WHERE username = '" + fixedUsername + "'";
                break;
        }

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                results.add(rs.getString("username"));
            }
        }
        return results;
    }
}
