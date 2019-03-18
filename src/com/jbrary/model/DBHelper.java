package com.jbrary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    private static DBHelper ourInstance = new DBHelper();
    private Connection connection;

    static {
        try {
            ourInstance.open();
            Statement statement = ourInstance.connection.createStatement();
//            ourInstance.connection.setAutoCommit(false);
//            statement.execute(Query.CREATE_BOOK_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ourInstance.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DBHelper getInstance() {
        return ourInstance;
    }

    private DBHelper() {
    }

    public void open() throws SQLException {
        connection = DriverManager.getConnection(Query.CONNECTION_STRING);
    }

    public void close() throws SQLException {
        connection.close();
    }


}
