package com.jbrary.model;

public class Book {
    private int id;
    private  String author;
    private String title;
    private String publisher;
    private int year;
    private String edition;
    private int quantity;

    public Book(int id, String author, String title, String publisher, int year, String edition, int quantity) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.edition = edition;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getEdition() {
        return edition;
    }

    public int getQuantity() {
        return quantity;
    }
}
