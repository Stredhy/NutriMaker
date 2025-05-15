package com.javafx.nutrimaker.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String walletPath = System.getenv("NUTRIMAKER_WALLET_PATH").replace("\\", "/");
    private static final String url= "jdbc:oracle:thin:@nutrimaker_medium?TNS_ADMIN=" + walletPath;

    private static final String username= "NUTRICIONISTA";
    private static final String password= "6m88Zp^t29JG*MXZsY!&";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}



