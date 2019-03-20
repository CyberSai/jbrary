package com.jbrary.model;

import java.nio.file.FileSystems;

final class Query {
     class Book {
         static final String ID = "_id";
         static final String AUTHOR = "author";
         static final String TITLE = "title";
         static final String PUBLISHER = "publisher";
         static final String YEAR = "year";
         static final String EDITION =  "edition";
         static final String QUANTITY =  "quantity";
         static final int ID_INDEX = 1;
         static final int AUTHOR_INDEX = 2;
         static final int TITLE_INDEX = 3;
         static final int PUBLISHER_INDEX = 4;
         static final int YEAR_INDEX = 5;
         static final int EDITION_INDEX = 6;
         static final int QUANTITY_INDEX = 7;
         static final String TABLE = "books";
    }
    static final String CONNECTION_STRING = "jdbc:sqlite:" + FileSystems.getDefault()
            .getPath("jbrary.sqlite").toAbsolutePath().toString();
    static final String CREATE_BOOK_TABLE = "CREATE table IF NOT EXISTS " + Book.TABLE + "( " + Book.ID +
            " INTEGER PRIMARY KEY, " + Book.AUTHOR + " TEXT, " + Book.TITLE + " TEXT, " + Book.PUBLISHER +
            " TEXT, " + Book.YEAR + " INTEGER, " + Book.EDITION + " TEXT, " + Book.QUANTITY + " INTEGER)";
    static final String SELECT_ALL_BOOKS = "SELECT * FROM " + Book.TABLE;
    static final String INSERT_BOOK = "INSERT INTO " + Book.TABLE + "(" + Book.AUTHOR + ", " + Book.TITLE +
            ", " + Book.PUBLISHER + ", " + Book.YEAR + ", " + Book.EDITION + ", " + Book.QUANTITY +
            ") VALUES(?, ?, ?, ?, ?, ?)";
    static final String UPDATE_BOOK = "UPDATE " + Book.TABLE + " SET " + Book.AUTHOR + " = ?," +
            Book.TITLE + " = ?," + Book.PUBLISHER + " = ?," + Book.YEAR + " = ?," + Book.EDITION +
            " = ?," + Book.QUANTITY + " = ? WHERE " + Book.ID + "= ?";
    static final String DELETE_BOOK = "DELETE FROM " + Book.TABLE + " WHERE " + Book.ID + " = ?";
    static final String SEARCH_BOOK_BY_TITLE = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.TITLE + " LIKE ?";
    static final String SEARCH_BOOK_BY_AUTHOR = "SELECT * FROM " + Book.TABLE + " WHERE " + Book.AUTHOR + " LIKE ?";
}
