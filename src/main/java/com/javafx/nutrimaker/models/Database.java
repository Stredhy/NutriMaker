package com.javafx.nutrimaker.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url= "jdbc:oracle:thin:@//adb.mx-queretaro-1.oraclecloud.com:1522/nutrimaker_medium.adb.oraclecloud.com";

    private static final String username= "NUTRICIONISTA";
    private static final String password= "6m88Zp^t29JG*MXZsY!&";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}



