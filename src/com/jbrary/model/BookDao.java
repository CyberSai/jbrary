package com.jbrary.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    static List<Book> all(){
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SELECT_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(
                        new Book(
                                resultSet.getInt(Query.Book.ID_INDEX),
                                resultSet.getString(Query.Book.AUTHOR_INDEX),
                                resultSet.getString(Query.Book.TITLE_INDEX),
                                resultSet.getString(Query.Book.PUBLISHER_INDEX),
                                resultSet.getInt(Query.Book.YEAR_INDEX),
                                resultSet.getString(Query.Book.EDITION_INDEX),
                                resultSet.getInt(Query.Book.QUANTITY_INDEX)
                        )
                );
            }
            statement.close();
            return books;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get all books");
            e.printStackTrace();
            return new ArrayList<Book>();
        }

    }

    static void insert(Book book){
        try (PreparedStatement statement = DBHelper.getInstance().prepare(Query.INSERT_BOOK)) {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setString(5, book.getEdition());
            statement.setInt(6, book.getQuantity());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying add new book");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DBHelper.getInstance().open();
        Book book = new Book(1, "Mavis", "Intro to C", "C++ Girls", 2018, "1st Edition", 125);
        insert(book);
        List<Book> books = all();
        books.stream().forEach(b -> System.out.println(b.getTitle()));
        DBHelper.getInstance().close();
    }
}
