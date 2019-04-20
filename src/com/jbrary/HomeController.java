package com.jbrary;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private JFXButton stockButton;

    public void onStockButtonClicked(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock.fxml"));
        Scene scene = new Scene(root,1000, 650);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
