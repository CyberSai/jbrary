package com.jbrary.ui.login;

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
        if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/jbrary/ui/home/home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
