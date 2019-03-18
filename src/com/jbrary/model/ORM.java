package com.jbrary.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ORM {
    private static ORM ourInstance = new ORM();
    private Connection connection;

    public static ORM getInstance() {
        return ourInstance;
    }

    private ORM() {
    }

    public void open() throws SQLException {
        connection = DriverManager.getConnection(Query.CONNECTION_STRING);
    }

    public void close() throws SQLException {
        connection.close();
    }
}
