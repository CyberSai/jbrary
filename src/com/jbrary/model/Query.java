package com.jbrary.model;

import java.nio.file.FileSystems;

final class Query {
    static final String CONNECTION_STRING = FileSystems.getDefault().getPath("jbrary.db").toAbsolutePath().toString();
    static final String BOOK_ID = "_id";
    static final String BOOK_AUTHOR = "author";
    static final String BOOK_TITLE = "title";
    static final String BOOK_PUBLISHER = "publisher";
    static final String BOOK_YEAR = "year";
    static final String BOOK_EDITION =  "edition";
    static final String BOOK_QUANTITY =  "quantity";
}
