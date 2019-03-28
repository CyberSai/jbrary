package com.jbrary.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int id;
    private User user;
    private Book book;
    private LocalDate orderDate;
    private LocalDate dueDate;

    public Order(int id, User user, Book book, LocalDate orderDate, LocalDate dueDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }

    public Order(int id, User user, Book book, String orderDate, String dueDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.orderDate = LocalDate.parse(orderDate, dateTimeFormatter);
        this.dueDate = LocalDate.parse(dueDate, dateTimeFormatter);
    }

    public Order(User user, Book book, LocalDate orderDate, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getOrderDateString() {
        return dateTimeFormatter.format(orderDate);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        return dateTimeFormatter.format(dueDate);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
