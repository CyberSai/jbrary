package com.jbrary.ui.bookissue;

import com.jbrary.Main;
import com.jbrary.model.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class IssueNewBookController implements Initializable {


    @FXML
    JFXComboBox<User> memberComboBox;
    @FXML
    JFXComboBox<Book> bookComboBox;
    @FXML
    JFXDatePicker returnDate;

    @FXML
    StackPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        bookComboBox.getItems()
                .addAll(BookDao.all());
        bookComboBox.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book object) {
                return object==null? "" : object.getTitle();
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });

        List<Label> memberLabels = new ArrayList<>();
        UserDao.all().forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                memberLabels.add(new Label(user.getName()));
            }
        });
        memberComboBox.getItems()
                .addAll(UserDao.all());

        memberComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object==null? "" : object.getName();
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });
    }

    public void onIssueBookButtonClicked(ActionEvent e){

        try {
            User user = memberComboBox.getSelectionModel().getSelectedItem();
            Book book = bookComboBox.getSelectionModel().getSelectedItem();

            LocalDate dueDate = returnDate.getValue();

            Order order = new Order(user, book, LocalDate.now(), dueDate, false);

            OrderDao.insert(order);
            Stage stage = (Stage) bookComboBox.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jbrary/ui/bookissue/bookissue.fxml"));
            Parent root = (Parent) loader.load();

            //Get controller of scene2
            BookIssueController bookIssueController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            bookIssueController.reloadList();
            bookIssueController.orderTable=null;

            Main.eventBus.post(new Main.DatabaseChangeEvent());


        }
        catch (Exception exception){
            System.err.println("Error occurred");
            exception.printStackTrace();
        }

    }
}
