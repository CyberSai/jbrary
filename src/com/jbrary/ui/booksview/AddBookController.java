package com.jbrary.ui.booksview;

import com.jbrary.model.Book;
import com.jbrary.model.BookDao;
import com.jbrary.ui.util.ImageUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    JFXButton chooseCover;
    @FXML
    ImageView imageView;

    File selectedImageFile;


    @FXML
    JFXTextField title, author, description,
    yearPublished, quantity, publisher;



    public void onChooseCoverPhotoButtonClicked(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.jpeg","*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(chooseCover.getScene().getWindow());
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(selectedFile.getPath());
            Image image = new Image(inputstream,100,0,true, true);


            selectedImageFile =selectedFile;


            imageView.setImage(image);

            System.out.println(ImageUtil.encodeImageToBase64(selectedImageFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       ;

    }

    public void onAddBookButtonClicked(ActionEvent e){

        try {

            Book book = new Book(author.getText(), title.getText(),
                    publisher.getText(),Integer.valueOf(yearPublished.getText()),
            "",Integer.valueOf(quantity.getText()),description.getText(),
            ImageUtil.encodeImageToBase64(selectedImageFile));
            BookDao.insert(book);
            Stage stage = (Stage) quantity.getScene().getWindow();
            stage.close();



        }
        catch (Exception exception){
            System.err.println("Error occurred");
            exception.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        yearPublished.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearPublished.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }
}
