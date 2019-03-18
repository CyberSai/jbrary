package com.jbrary.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private static PreparedStatement statement;

    static List<Book> all(){
        try {
            statement = DBHelper.getInstance().prepare("SELECT * FROM " + Query.BOOK_TABLE);
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(
                        new Book(
                                resultSet.getInt(Query.BOOK_ID_INDEX),
                                resultSet.getString(Query.BOOK_AUTHOR_INDEX),
                                resultSet.getString(Query.BOOK_TITLE_INDEX),
                                resultSet.getString(Query.BOOK_PUBLISHER_INDEX),
                                resultSet.getInt(Query.BOOK_YEAR_INDEX),
                                resultSet.getString(Query.BOOK_EDITION_INDEX),
                                resultSet.getInt(Query.BOOK_QUANTITY_INDEX)
                        )
                );
            }
            return books;
        } catch (SQLException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
            return new ArrayList<Book>();
        }

    }

    public static void main(String[] args) throws SQLException {
        DBHelper.getInstance().open();
        List<Book> books = all();
        books.stream().forEach(b -> System.out.println(b.getTitle()));
        DBHelper.getInstance().close();
    }
}
