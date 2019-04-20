package com.jbrary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private GridPane loginPane;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton loginButton;

    public void onLoginButtonClicked(ActionEvent e) throws Exception {
        if (usernameField.getText().equals("jb") && passwordField.getText().equals("pass")) {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root, 1000, 650);
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
