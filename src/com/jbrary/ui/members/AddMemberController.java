package com.jbrary.ui.members;

import com.jbrary.model.User;
import com.jbrary.model.UserDao;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {


    @FXML
    JFXComboBox<Label> levelComboBox;
    @FXML
    JFXComboBox<Label> hallComboBox;
    @FXML
    JFXTextField nameInput, programmeInput;
    ToggleGroup genderGroup;
    @FXML
    JFXRadioButton maleRadioButton, femaleRadioButton;
    @FXML
    JFXDatePicker datePicker;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(genderGroup);
        maleRadioButton.setUserData("Male");
        femaleRadioButton.setUserData("Female");
        femaleRadioButton.setToggleGroup(genderGroup);
        levelComboBox.getItems()
                .addAll(new Label("Level 100"),
                        new Label("Level 200"),
                        new Label("Level 300"),
                        new Label("Level 400"));
        levelComboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });

        hallComboBox.getItems()
                .addAll(new Label("Jean Nelson Aka Hall"),
                        new Label("Elizabeth Frances Sey Hall"),
                        new Label("Alexander Adum Kwapong Hall"),
                        new Label("Dr. Hilla Limann Hall"),
                        new Label("Jubilee Hall"),
                        new Label("Mensah Sarbah Hall"),
                        new Label("Akuafo Hall"),
                        new Label("Legon Hall"),
                        new Label("Volta Hall"),
                        new Label("Commonwealth Hall"),
                        new Label("Pentagon Hostel"),
                        new Label("International Students Hostel(ISH)"),
                        new Label("Valco Graduate Hostel"),
                        new Label("Bani Hostel"),
                        new Label("James Top Nelson Yankah Hall"));
        hallComboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });
    }

    public void onAddNewMemberButtonClicked(ActionEvent e){

        try {


            User user = new User(nameInput.getText(), datePicker.getValue(), (String)genderGroup.getSelectedToggle().getUserData(),
                    Integer.valueOf(levelComboBox.getSelectionModel().getSelectedItem().getText().replace("Level ","")),
                    programmeInput.getText(), hallComboBox.getSelectionModel().getSelectedItem().getText(),
                    "");
            UserDao.insert(user);
            Stage stage = (Stage) levelComboBox.getScene().getWindow();
            stage.close();



        }
        catch (Exception exception){
            System.err.println("Error occurred");
            exception.printStackTrace();


        }

    }
}
