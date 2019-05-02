package com.jbrary.model;

import java.nio.file.FileSystems;

final class Query {
    static final String CONNECTION_STRING = "jdbc:sqlite:" + FileSystems.getDefault()
            .getPath("jbrary.sqlite").toAbsolutePath().toString();

    class Book {
        static final String ID = "_id";
        static final String AUTHOR = "author";
        static final String TITLE = "title";
        static final String PUBLISHER = "publisher";
        static final String YEAR = "year";
        static final String EDITION =  "edition";
        static final String IDENTIFIER =  "identifier";
        static final String DESCRIPTION = "description";
        static final String QUANTITY = "quantity";
        static final String IMAGE = "image";
     /*   static final int ID_INDEX = 1;
        static final int AUTHOR_INDEX = 2;
        static final int TITLE_INDEX = 3;
        static final int PUBLISHER_INDEX = 4;
        static final int YEAR_INDEX = 5;
        static final int EDITION_INDEX = 6;
        static final int IDENTIFIER_INDEX = 7;
        static final int DESCRIPTION_INDEX = 8;
        static final int IMAGE_INDEX = 8;
        static final int QUANTITY_INDEX = 7;*/
     static final int ID_INDEX = 1;
        static final int AUTHOR_INDEX = 2;
        static final int TITLE_INDEX = 3;
        static final int PUBLISHER_INDEX = 4;
        static final int YEAR_INDEX = 5;
        static final int EDITION_INDEX = 6;
        static final int QUANTITY_INDEX = 7;
        static final int DESCRIPTION_INDEX = 8;
        static final int IMAGE_INDEX = 9;
        static final String TABLE = "books";
    }

   /* static final String CREATE_BOOKS_TABLE = "CREATE table IF NOT EXISTS " + Book.TABLE + "( " + Book.ID +
            " INTEGER PRIMARY KEY, " + Book.AUTHOR + " TEXT, " + Book.TITLE + " TEXT NOT NULL, " + Book.PUBLISHER +
            " TEXT, " + Book.YEAR + " INTEGER, " + Book.EDITION + " TEXT, " + Book.IDENTIFIER + " INTEGER, " +
            Book.DESCRIPTION + " TEXT, " + Book.IMAGE + " TEXT)";*/
   static final String CREATE_BOOKS_TABLE = "CREATE table IF NOT EXISTS " + Book.TABLE + "( " + Book.ID +
           " INTEGER PRIMARY KEY, " + Book.AUTHOR + " TEXT, " + Book.TITLE + " TEXT NOT NULL, " + Book.PUBLISHER +
           " TEXT, " + Book.YEAR + " INTEGER, " + Book.EDITION + " TEXT, " + Book.QUANTITY + " INTEGER, " +
           Book.DESCRIPTION + " TEXT, " + Book.IMAGE + " TEXT)";
    static final String SELECT_ALL_BOOKS = "SELECT * FROM " + Book.TABLE;
    /*static final String INSERT_BOOK = "INSERT INTO " + Book.TABLE + "(" + Book.AUTHOR + ", " + Book.TITLE +
            ", " + Book.PUBLISHER + ", " + Book.YEAR + ", " + Book.EDITION +
            ", " + Book.DESCRIPTION + ", " + Book.IMAGE +
            ") VALUES(?, ?, ?, ?, ?, ?, ?)";*/
    static final String INSERT_BOOK = "INSERT INTO " + Book.TABLE + "(" + Book.AUTHOR + ", " + Book.TITLE +
            ", " + Book.PUBLISHER + ", " + Book.YEAR + ", " + Book.EDITION + ", " + Book.QUANTITY +
            ", " + Book.DESCRIPTION + ", " + Book.IMAGE +
            ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    static final String UPDATE_BOOK = "UPDATE " + Book.TABLE + " SET " + Book.AUTHOR + " = ?," +
            Book.TITLE + " = ?," + Book.PUBLISHER + " = ?," + Book.YEAR + " = ?," + Book.EDITION +
            " = ?," + Book.IDENTIFIER + " = ?," + Book.DESCRIPTION + " = ?," + Book.IMAGE + " = ? WHERE " + Book.ID + "= ?";
    static final String DELETE_BOOK = "DELETE FROM " + Book.TABLE + " WHERE " + Book.ID + " = ?";
    static final String FIND_BOOK = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.ID + " = ?";
    static final String SEARCH_BOOK_BY_TITLE = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.TITLE + " LIKE ?";
    static final String SEARCH_BOOK_BY_AUTHOR = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.AUTHOR + " LIKE ?";
    static final String SEARCH_BOOK_BY_YEAR = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.YEAR + " LIKE ?";

    class User {
        static final String ID = "_id";
        static final String NAME = "name";
        static final String DATE_OF_BIRTH = "date_of_birth";
        static final String GENDER = "gender";
        static final String LEVEL = "level";
        static final String PROGRAM = "program";
        static final String RESIDENCE = "residence";
        static final String IMAGE = "image";
        static final int ID_INDEX = 1;
        static final int NAME_INDEX = 2;
        static final int DATE_OF_BIRTH_INDEX = 3;
        static final int GENDER_INDEX = 4;
        static final int LEVEL_INDEX = 5;
        static final int PROGRAM_INDEX = 6;
        static final int RESIDENCE_INDEX = 7;
        static final int IMAGE_INDEX = 8;
        static final String TABLE = "users";
    }

    static final String CREATE_USERS_TABLE = "CREATE table IF NOT EXISTS " + User.TABLE + "( " + User.ID +
            " INTEGER PRIMARY KEY, " + User.NAME + " TEXT, " + User.DATE_OF_BIRTH + " TEXT, " +
            User.GENDER + " TEXT, " + User.LEVEL + " INTEGER, " + User.PROGRAM + " TEXT, " + User.RESIDENCE +
            " TEXT, " + User.IMAGE + " TEXT)";
    static final String SELECT_ALL_USERS = "SELECT * FROM " + User.TABLE;
    static final String INSERT_USER = "INSERT INTO " + User.TABLE + "(" + User.NAME + ", " + User.DATE_OF_BIRTH +
            ", " + User.GENDER + ", " + User.LEVEL + ", " + User.PROGRAM +
            ", " + User.RESIDENCE + ", " + User.IMAGE +
            ") VALUES(?, ?, ?, ?, ?, ?, ?)";
    static final String UPDATE_USER = "UPDATE " + User.TABLE + " SET " + User.NAME + " = ?," +
            User.DATE_OF_BIRTH + " = ?," + User.GENDER + " = ?," + User.LEVEL + " = ?," + User.PROGRAM +
            " = ?," + User.RESIDENCE + " = ?," + Book.IMAGE + " = ? WHERE " + User.ID + "= ?";
    static final String DELETE_USER = "DELETE FROM " + User.TABLE + " WHERE " + User.ID + " = ?";
    static final String FIND_USER = "SELECT * FROM " + User.TABLE + " WHERE " + User.ID + " = ?";
    static final String SEARCH_USER_BY_NAME = "SELECT * FROM " + User.TABLE + " WHERE " + User.NAME + " LIKE ?";

    class Order {
        static final String ID = "_id";
        static final String USER_ID = "user_id";
        static final String BOOK_ID = "book_id";
        static final String ORDER_DATE = "order_date";
        static final String DUE_DATE = "due_date";
        static final String FULFILLED = "fulfilled";
        static final int ID_INDEX = 1;
        static final int USER_ID_INDEX = 2;
        static final int BOOK_ID_INDEX = 3;
        static final int ORDER_DATE_INDEX = 4;
        static final int DUE_DATE_INDEX = 5;
        static final int FULFILLED_INDEX = 6;

        static final String TABLE = "orders";
    }

    static final String CREATE_ORDERS_TABLE = "CREATE table IF NOT EXISTS " + Order.TABLE + "( " + Order.ID +
            " INTEGER PRIMARY KEY, " + Order.USER_ID + " INTEGER, " + Order.BOOK_ID + " INTEGER, " +
            Order.ORDER_DATE + " TEXT, " + Order.DUE_DATE + " TEXT, " + Order.FULFILLED + " TEXT)";
    static final String SELECT_ALL_ORDERS = "SELECT * FROM " + Order.TABLE;
    static final String INSERT_ORDER = "INSERT INTO " + Order.TABLE + "(" + Order.USER_ID + ", " + Order.BOOK_ID +
            ", " + Order.ORDER_DATE + ", " + Order.DUE_DATE + ", " + Order.FULFILLED + ") VALUES(?, ?, ?, ?, ?)";
    static final String UPDATE_ORDER = "UPDATE " + Order.TABLE + " SET " + Order.USER_ID + " = ?," +
            Order.BOOK_ID + " = ?," + Order.ORDER_DATE + " = ?," + Order.DUE_DATE + " = ?," +
            Order.FULFILLED + " = ? WHERE " + Order.ID + "= ?";
    static final String DELETE_ORDER = "DELETE FROM " + Order.TABLE + " WHERE " + Order.ID + " = ?";
}
