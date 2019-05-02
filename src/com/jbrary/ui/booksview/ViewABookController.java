package com.jbrary.ui.booksview;

import com.jbrary.Main;
import com.jbrary.model.Book;
import com.jbrary.model.BookDao;
import com.jbrary.ui.util.ImageUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewABookController implements Initializable {

    @FXML
    JFXButton chooseCover;
    @FXML
    ImageView imageView;

    File selectedImageFile;


    @FXML
    Label title, author, description,
    yearPublished, quantity, publisher;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;

        System.out.println(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Book book = Main.currentBook;
        title.setText(book.getTitle());
        publisher.setText(book.getPublisher());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());
        yearPublished.setText(String.valueOf(book.getYear()));

        try {
            imageView.setImage(ImageUtil.decodeImageFromBase64(book.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
