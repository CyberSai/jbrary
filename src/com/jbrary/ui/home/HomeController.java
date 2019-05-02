package com.jbrary.ui.home;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    AnchorPane contentPane;


    @FXML
    JFXButton issuedBooksTab;


    public void onBooksIssuedTabClicked(ActionEvent e) throws  Exception{
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/com/jbrary/ui/bookissue/bookissue.fxml"));
        contentPane.getChildren().setAll(pane);

    }
    public void onMemberTabClicked(ActionEvent e) throws  Exception{
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/com/jbrary/ui/members/members.fxml"));
        contentPane.getChildren().setAll(pane);

    }

    public void onBooksTabClicked(ActionEvent e) throws  Exception{
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/com/jbrary/ui/booksview/booksview.fxml"));
        contentPane.getChildren().setAll(pane);

    }

    public void onQuitTabClicked(ActionEvent e) throws  Exception{
       System.exit(0);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            AnchorPane pane = null;
            pane = FXMLLoader.load(this.getClass().getResource("/com/jbrary/ui/bookissue/bookissue.fxml"));
            //contentPane.getChildren().add(pane);
            contentPane.getChildren().addAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
