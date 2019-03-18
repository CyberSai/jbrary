package com.jbrary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene welcome;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setTitle("JBrary");
        welcome = new Scene(root, 600, 400);
        primaryStage.setScene(welcome);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
