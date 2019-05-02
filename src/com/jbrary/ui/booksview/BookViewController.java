
package com.jbrary.ui.booksview;

import com.jbrary.Main;
import com.jbrary.model.Book;
import com.jbrary.model.BookDao;
import com.jbrary.model.User;
import com.jbrary.model.UserDao;
import com.jbrary.ui.util.DialogUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sun.misc.BASE64Decoder;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class BookViewController {
    private static final int NO_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int AUTHOR_INDEX = 2;
    private static final int STATE_INDEX = 3;
    private static final int USER_INDEX = 4;
    private static final int RT_DAY_INDEX_INDEX = 5;

    @FXML
    AnchorPane root;
    @FXML
    GridPane booksGrid;
    @FXML
    ScrollPane scrollPane;

    @FXML
    JFXTextField search;
    @FXML
    JFXComboBox<Label> filter;

    @FXML
    JFXButton refresh;


    public void onNewBookButtonClicked(ActionEvent e) throws Exception {

        DialogUtil.openModalWindow(root, this.getClass(),
                "/com/jbrary/ui/booksview/addbook.fxml", "Add New Book");
    }

    public void initialize() {
        populateGridWithBooks(BookDao.all());

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                populateGridWithBooks(BookDao.all());
            }
        });

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);

                List<Book> foundBooks;
                switch (filter.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        foundBooks = BookDao.searchByTitle(newValue);
                        break;
                    case 1:
                        foundBooks = BookDao.searchByYear(Integer.valueOf(newValue));
                        break;
                    case 2:
                        foundBooks = BookDao.searchByAuthor(newValue);
                        break;

                    default:
                        foundBooks = BookDao.searchByTitle(newValue);
                        break;

                }


                System.out.println(foundBooks);

                populateGridWithBooks(foundBooks);
            }
        });


        filter.getItems()
                .addAll(new Label("By Title"),
                        new Label("By Year"),
                        new Label("By Author"));
        filter.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object == null ? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });


    }

    private void populateGridWithBooks(List<Book> books) {
        booksGrid.getChildren().removeAll(booksGrid.getChildren());
        int row = 0, col = 0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            VBox vBox = new VBox();

            try {
                vBox.getChildren().add(new ImageView(getImageFromBase64String(book.getImage())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            vBox.getChildren().add(new Label(book.getTitle()));
            vBox.getChildren().add(new Label(book.getAuthor()));

            vBox.setPadding(new Insets(10, 10, 10, 10));
            booksGrid.add(vBox, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }

            vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Am here");
                    try {

                        Main.currentBook = book;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jbrary/ui/booksview/viewbook.fxml"));
                        Parent win = FXMLLoader.load(getClass().getResource("/com/jbrary/ui/booksview/viewbook.fxml"));
                        Stage newWindow = new Stage();



                        newWindow.setTitle(book.getTitle());
                        // Specifies the modality for new window.
                        newWindow.initModality(Modality.WINDOW_MODAL);
                        newWindow.setResizable(false);
                        newWindow.setX(root.getScene().getWindow().getX() + 200);
                        newWindow.setY(root.getScene().getWindow().getY() - 50);
                        newWindow.setMinWidth(500);
                        newWindow.setScene(new Scene(win));



                        // Specifies the owner Window (parent) for new window
                        newWindow.initOwner(root.getScene().getWindow());
                        newWindow.show();

                        /*DialogUtil.openModalWindow(root, this.getClass(),
                                "/com/jbrary/ui/booksview/viewbook.fxml", book.getTitle());*/


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
    }

    private Image getImageFromBase64String(String newValue) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(base64Decoder.decodeBuffer(newValue));
        Image img = new Image(inputStream, 170, 0, true, true);

        return img;
    }

}

