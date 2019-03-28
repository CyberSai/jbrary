package com.jbrary.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> all() {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            sqliteQueryToUser(resultSet, users);
            return users;
        } catch (SQLException e) {
            System.out.println("An error occurred while trying to get all users");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void insert(User user) {
        try (PreparedStatement statement = DBHelper.getInstance().prepare(Query.INSERT_USER)) {
            userToSqliteQuery(user, statement);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying add new user");
            e.printStackTrace();
        }
    }

    public static void update(User user) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.UPDATE_USER)) {
            userToSqliteQuery(user, statement);
            statement.setInt(8, user.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying update user");
            e.printStackTrace();
        }
    }

    public static void delete(User user) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.DELETE_USER)) {
            statement.setInt(1, user.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error occurred while trying delete user");
            e.printStackTrace();
        }
    }

    public static List<User> searchByName(String name) {
        try(PreparedStatement statement = DBHelper.getInstance().prepare(Query.SEARCH_USER_BY_NAME)) {
            return searchHelp(name, statement);
        } catch (SQLException e) {
            System.out.println("An error occurred while trying search for user by name");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<User> searchHelp(String field, PreparedStatement statement) throws SQLException {
        List<User> users = new ArrayList<>();
        statement.setString(1, "%" + field + "%");
        ResultSet resultSet =  statement.executeQuery();
        sqliteQueryToUser(resultSet, users);
        return users;
    }

    private static void sqliteQueryToUser(ResultSet resultSet, List<User> users) throws SQLException {
        while (resultSet.next()) {
            users.add(
                    new User(
                            resultSet.getInt(Query.User.ID_INDEX),
                            resultSet.getString(Query.User.NAME_INDEX),
                            resultSet.getString(Query.User.DATE_OF_BIRTH_INDEX),
                            resultSet.getString(Query.User.GENDER_INDEX),
                            resultSet.getInt(Query.User.LEVEL_INDEX),
                            resultSet.getString(Query.User.PROGRAM_INDEX),
                            resultSet.getString(Query.User.RESIDENCE_INDEX),
                            resultSet.getString(Query.User.IMAGE_INDEX)
                    )
            );
        }
    }

    private static void userToSqliteQuery(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getDateOfBirthString());
        statement.setString(3, user.getGender());
        statement.setInt(4, user.getLevel());
        statement.setString(5, user.getProgram());
        statement.setString(6, user.getResidence());
        statement.setString(7, user.getImage());
    }

    public static void main(String[] args) throws SQLException {
        DBHelper.getInstance().open();
//        User user = new User(2,"Bernice Hill", LocalDate.now(), "female", 200, "Agricultural Engineering", "Sarbah Hall", "c:/princess");
//        delete(user);
        List<User> users = searchByName("amina");
        users.forEach(u -> System.out.println(u.getId() + ":" + u.getName() + ":" + u.getDateOfBirthString()));
        DBHelper.getInstance().close();
    }
}
