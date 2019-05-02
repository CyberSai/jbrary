package com.jbrary.model;



public class Book {
    private int id;
    private  String author;
    private String title;
    private String publisher;
    private int year;
    private String edition;
    private int quantity;
    private String description;
    private String image;

    public Book() {
    }


    public Book(int id, String author, String title, String publisher, int year, String edition, int quantity, String description, String image) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.edition = edition;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }

    public Book(String author, String title, String publisher, int year, String edition, int quantity, String description, String image) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.edition = edition;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
