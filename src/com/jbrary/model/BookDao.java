package com.jbrary.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public static List<Book> all() {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SELECT_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            sqliteQueryToBook(resultSet, books);
            return books;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get all books");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void insert(Book book) {
        try (PreparedStatement statement = DBHelper.getInstance().prepare(Query.INSERT_BOOK)) {
            bookToSqliteQuery(book, statement);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying add new book");
            e.printStackTrace();
        }
    }

    public static void update(Book book) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.UPDATE_BOOK)) {
            bookToSqliteQuery(book, statement);
            statement.setInt(9, book.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying update book");
            e.printStackTrace();
        }
    }

    public static Book find(int id) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.FIND_BOOK)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return new Book(
                    resultSet.getInt(Query.Book.ID_INDEX),
                    resultSet.getString(Query.Book.AUTHOR_INDEX),
                    resultSet.getString(Query.Book.TITLE_INDEX),
                    resultSet.getString(Query.Book.PUBLISHER_INDEX),
                    resultSet.getInt(Query.Book.YEAR_INDEX),
                    resultSet.getString(Query.Book.EDITION_INDEX),
                    resultSet.getInt(Query.Book.QUANTITY_INDEX),
                    resultSet.getString(Query.Book.DESCRIPTION_INDEX),
                    resultSet.getString(Query.Book.IMAGE_INDEX)
            );
        } catch (SQLException e) {
            System.out.println("An error occurred while trying find book");
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Book book) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.DELETE_BOOK)) {
            statement.setInt(1, book.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying delete book");
            e.printStackTrace();
        }
    }

    public static List<Book> searchByTitle(String title) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_TITLE)) {
            return searchHelp(title, statement);
        } catch (SQLException e) {
            System.out.println("An error occurred while trying search for book by title");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Book> searchByAuthor(String author) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_AUTHOR)) {
            return searchHelp(author, statement);
        } catch (SQLException e) {
            System.out.println("An error occurred while trying search for book by author");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Book> searchByYear(int year) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_YEAR)) {
            return searchHelp(Integer.toString(year), statement);
        } catch (SQLException e) {
            System.out.println("An error occurred while trying search for book by author");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Book> searchHelp(String field, PreparedStatement statement) throws SQLException {
        List<Book> books = new ArrayList<>();
        statement.setString(1, "%" + field + "%");
        ResultSet resultSet =  statement.executeQuery();
        sqliteQueryToBook(resultSet, books);
        return books;
    }

    private static void sqliteQueryToBook(ResultSet resultSet, List<Book> books) throws SQLException {
        while (resultSet.next()) {
            books.add(
                    new Book(
                            resultSet.getInt(Query.Book.ID_INDEX),
                            resultSet.getString(Query.Book.AUTHOR_INDEX),
                            resultSet.getString(Query.Book.TITLE_INDEX),
                            resultSet.getString(Query.Book.PUBLISHER_INDEX),
                            resultSet.getInt(Query.Book.YEAR_INDEX),
                            resultSet.getString(Query.Book.EDITION_INDEX),
                            resultSet.getInt(Query.Book.QUANTITY_INDEX),
                            resultSet.getString(Query.Book.DESCRIPTION_INDEX),
                            resultSet.getString(Query.Book.IMAGE_INDEX)
                    )
            );
        }
    }

    private static void bookToSqliteQuery(Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getPublisher());
        statement.setInt(4, book.getYear());
        statement.setString(5, book.getEdition());
        statement.setInt(6, book.getQuantity());
        statement.setString(7, book.getDescription());
        statement.setString(8, book.getImage());
    }

    public static void main(String[] args) throws SQLException {
        DBHelper.getInstance().open();
        Book book = find(1);//new Book(1,"Mavis Mensah Again", "Intro to C Again", "C++ Girls", 2018, "1st Edition", 125, "I nice book", "book.jpg");
//        update(book);
        System.out.println(book.getTitle());
//        List<Book> books = all();
//        books.forEach(b -> System.out.println(b.getId() + ":" + b.getTitle() + ":" + b.getAuthor()));
        DBHelper.getInstance().close();
    }
}
