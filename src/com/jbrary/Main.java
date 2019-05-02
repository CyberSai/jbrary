package com.jbrary;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jbrary.model.Book;
import com.jbrary.model.BookDao;
import com.jbrary.model.DBHelper;
import com.jbrary.ui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static final EventBus eventBus = new EventBus();

    public static Book currentBook= new Book();

    public static class DatabaseChangeEvent{
        private Controller controller;

        public DatabaseChangeEvent(Controller controller) {

            this.controller = controller;
        }

        public DatabaseChangeEvent() {

        }

        @Subscribe
        public void databaseEvent(DatabaseChangeEvent event) {
            //Do refresh
            System.out.println("Event listener was called");
            event.controller.onReloadList();
        }

    }

    public static void main(String[] args) {
	// write your code here





        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       // DatabaseChangeEvent databaseChangeEvent=  new DatabaseChangeEvent();



        Font.loadFont(getClass().getResource("/com/jbrary/fonts/HelveticaNeuBold.ttf").toExternalForm(), 10);

        Parent root = FXMLLoader.load(getClass().getResource("/com/jbrary/ui/login/login.fxml"));
        /*Parent root = FXMLLoader.load(getClass().getResource("/com/jbrary/ui/home/home.fxml"));*/
        primaryStage.setTitle("JBrary");
        primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream( "/com/jbrary/image/logo.png" )));
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        DBHelper.getInstance().open();
        System.out.println(BookDao.all().toString());
    }

    @Override
    public void stop() throws Exception {
        DBHelper.getInstance().close();
        super.stop();
    }

}
