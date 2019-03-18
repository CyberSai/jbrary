package com.jbrary.model;

import java.sql.*;

public class DBHelper {
    private static DBHelper ourInstance = new DBHelper();
    private Connection connection;

    static {
        try {
            ourInstance.open();
            Statement statement = ourInstance.connection.createStatement();
            statement.execute(Query.CREATE_BOOK_TABLE);
            statement.close();
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

    PreparedStatement prepare(String statement) throws SQLException {
         return ourInstance.connection.prepareStatement(statement);
    }
}
