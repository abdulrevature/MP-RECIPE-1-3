package com.revature.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static ConnectionUtil cu = null;
    private String username;
    private String password;
    private String url;

    private ConnectionUtil() {
        super();
    }

    public static ConnectionUtil getInstance() {
        if(cu == null) {
            cu = new ConnectionUtil();
        }
        return cu;
    }

    public ConnectionUtil configure(String username, String password, String url, Driver driver) throws SQLException {
        this.username = username;
        this.password = password;
        this.url = url;
        DriverManager.registerDriver(driver);
        return this;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
