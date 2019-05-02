package com.jbrary.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static List<Order> all() {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SELECT_ALL_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            sqliteQueryToOrder(resultSet, orders);
            return orders;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get all orders");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void insert(Order order) {
        try (PreparedStatement statement = DBHelper.getInstance().prepare(Query.INSERT_ORDER)) {
            orderToSqliteQuery(order, statement);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying add new order");
            e.printStackTrace();
        }
    }

    public static void update(Order order) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.UPDATE_ORDER)) {
            orderToSqliteQuery(order, statement);

            statement.setInt(6, order.getId());
            statement.execute();
            System.out.println(statement.toString());
        } catch (SQLException e) {
            System.out.println("An error occurred while trying update order");
            e.printStackTrace();
        }
    }

    public static void delete(Order order) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.DELETE_ORDER)) {
            statement.setInt(1, order.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying delete order");
            e.printStackTrace();
        }
    }

//    public static List<Order> searchByUserName(String name) {
//        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_USER_BY_NAME)) {
//            return searchHelp(name, statement);
//        } catch (SQLException e) {
//            System.out.println("An error occurred while trying search for user by name");
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

//    public static List<Order> searchByBookTitle(String title) {
//        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_TITLE)) {
//            return searchHelp(title, statement);
//        } catch (SQLException e) {
//            System.out.println("An error occurred while trying search for book by title");
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

//    public static List<Order> searchByBookAuthor(String author) {
//        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_AUTHOR)) {
//            return searchHelp(author, statement);
//        } catch (SQLException e) {
//            System.out.println("An error occurred while trying search for book by author");
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

//    public static List<Order> searchByBookYear(int year) {
//        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_BOOK_BY_YEAR)) {
//            return searchHelp(Integer.toString(year), statement);
//        } catch (SQLException e) {
//            System.out.println("An error occurred while trying search for book by author");
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }

    private static List<Order> searchHelp(String field, PreparedStatement statement) throws SQLException {
        List<Order> orders = new ArrayList<>();
        statement.setString(1, "%" + field + "%");
        ResultSet resultSet =  statement.executeQuery();
        sqliteQueryToOrder(resultSet, orders);
        return orders;
    }

    private static void sqliteQueryToOrder(ResultSet resultSet, List<Order> orders) throws SQLException {
        while (resultSet.next()) {
            orders.add(
                    new Order(
                            resultSet.getInt(Query.Order.ID_INDEX),
                            UserDao.find(resultSet.getInt(Query.Order.USER_ID_INDEX)),
                            BookDao.find(resultSet.getInt(Query.Order.BOOK_ID_INDEX)),
                            resultSet.getString(Query.Order.ORDER_DATE_INDEX),
                            resultSet.getString(Query.Order.DUE_DATE_INDEX),
                            resultSet.getString(Query.Order.FULFILLED_INDEX)
                    )
            );
        }
    }

    private static void orderToSqliteQuery(Order order, PreparedStatement statement) throws SQLException {
        statement.setInt(1, order.getUser().getId());
        statement.setInt(2, order.getBook().getId());
        statement.setString(3, order.getOrderDateString());
        statement.setString(4, order.getDueDateString());
        statement.setString(5, order.getFullfilled());
    }

    public static void main(String[] args) throws SQLException {
        DBHelper.getInstance().open();

        DBHelper.getInstance().close();
    }
}
