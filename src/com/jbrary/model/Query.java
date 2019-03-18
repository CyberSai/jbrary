package com.jbrary.model;

import java.nio.file.FileSystems;

final class Query {
    static final String CONNECTION_STRING = "jdbc:sqlite:" + FileSystems.getDefault().getPath("jbrary.db").toAbsolutePath().toString();
    static final String BOOK_ID = "_id";
    static final String BOOK_AUTHOR = "author";
    static final String BOOK_TITLE = "title";
    static final String BOOK_PUBLISHER = "publisher";
    static final String BOOK_YEAR = "year";
    static final String BOOK_EDITION =  "edition";
    static final String BOOK_QUANTITY =  "quantity";
    static final String BOOK_TABLE = "books";
    static final String CREATE_BOOK_TABLE = "CREATE table " + BOOK_TABLE + " IF NOT EXISTS( " + BOOK_ID +
            " INTEGER PRIMARY KEY, " + BOOK_AUTHOR + " TEXT, " + BOOK_TITLE + " TEXT, " + BOOK_PUBLISHER +
            " TEXT, " + BOOK_YEAR + " INTEGER, " + BOOK_EDITION + " TEXT, " + BOOK_QUANTITY + " INTEGER)";
}
