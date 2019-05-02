package com.jbrary.ui.bookissue;

import com.jbrary.Main;
import com.jbrary.model.*;
import com.jbrary.ui.Controller;
import com.jbrary.ui.util.DialogUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class BookIssueController extends Controller implements Initializable {

    @FXML
    TableView<Order> orderTable;
    @FXML
    AnchorPane root;
    @FXML
    TableColumn<Order, String> titleColumn,
            authorColumn, userColumn, returnColumn, finesColumn;
    @FXML
    TableColumn<Order, ImageView> actionsColumn;

    @FXML
    TableColumn<Order, JFXCheckBox> stateColumn;
    @FXML
    JFXButton refresh;
    @FXML
    JFXTextField search;

    @FXML
    JFXComboBox<Label> filter;

    public void onIssueNewBookButtonClicked(ActionEvent e) throws Exception {


        DialogUtil.openModalWindow(root, this.getClass(),
                "/com/jbrary/ui/bookissue/issuenewbook.fxml", "Issue New Book");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.eventBus.register(new Main.DatabaseChangeEvent(this));
        Main.eventBus.post(new Main.DatabaseChangeEvent());

        filter.getItems()
                .addAll(new Label("By User"),
                        new Label("By Book"));
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
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                List<Order> foundUsers = new ArrayList<>();
                getOrderList().forEach(new Consumer<Order>() {
                    @Override
                    public void accept(Order order) {

                        switch (filter.getSelectionModel().getSelectedIndex()) {
                            case 0:
                                if (order.getUser().getName().contains(newValue)) {
                                    foundUsers.add(order);
                                }
                                break;
                            case 1:
                                if (order.getBook().getTitle().contains(newValue)) {
                                    foundUsers.add(order);
                                }
                                break;

                            default:
                                if (order.getUser().getName().contains(newValue)) {
                                    foundUsers.add(order);
                                }
                                break;
                        }

                    }
                });
                System.out.println(foundUsers);

                orderTable.setItems(FXCollections.observableArrayList(foundUsers));
            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                orderTable.setItems(getOrderList());
            }
        });
        loadDataIntoTable();
    }

    public void loadDataIntoTable() {
        orderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));

        /*TableColumn<Order, String> titleColumn = new TableColumn<>();*/


        titleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getBook().getTitle()));
                return sp;
            }
        });

        authorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(param.getValue().getBook().getAuthor()));
                return sp;
            }
        });

        userColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(param.getValue().getUser().getName()));
                return sp;
            }
        });

        stateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, JFXCheckBox>, ObservableValue<JFXCheckBox>>() {
            @Override
            public ObservableValue<JFXCheckBox> call(TableColumn.CellDataFeatures<Order, JFXCheckBox> param) {
                ObjectProperty<JFXCheckBox> simpleObjectProperty
                        = new SimpleObjectProperty<JFXCheckBox>();
                JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setSelected(param.getValue().isFulfilled());

                checkBox.setDisable(true);
                simpleObjectProperty.setValue(checkBox);

                return simpleObjectProperty;
            }
        });
        returnColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(param.getValue().getDueDateString()));
                return sp;
            }
        });
        finesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(param.getValue().getFine()));
                return sp;
            }
        });

        orderTable.setRowFactory(new Callback<TableView<Order>, TableRow<Order>>() {
            @Override
            public TableRow<Order> call(TableView<Order> param) {
                final TableRow<Order> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();

                        if (button != MouseButton.SECONDARY) {
                            return;
                        }

                        ContextMenu contextMenu = new ContextMenu();

                        // create menuitems
                        MenuItem menuItem1 = new MenuItem("Set Book As Returned");


                        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                Order order = param.getSelectionModel().getSelectedItem();
                                order.setFulfilled(true);
                                OrderDao.update(order);

                                orderTable.setItems(getOrderList());
                            }
                        });
                        // add menu items to menu
                        contextMenu.getItems().add(menuItem1);

                        // create a tilepane
                        //TilePane tilePane = new TilePane(row);

                        // setContextMenu to label
                        row.setContextMenu(contextMenu);


                    }
                });

                return row;
            }
        });
       /* actionsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Order, ImageView> param) {

                ObjectProperty<ImageView> property = new SimpleObjectProperty<>();

                Image image = new Image("/com/jbrary/image/icons8-menu-vertical-32.png", 20,0, true,true);
                ImageView imageView = new ImageView(image);

                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Tile pressed ");
                        event.consume();
                    }
                });
                property.setValue(imageView);


                return property;
            }
        });*/

        orderTable.setItems(getOrderList());
    }

    private ObservableList<Order> getOrderList() {


        ObservableList<Order> list = FXCollections.observableArrayList(OrderDao.all());
        return list;
    }

    @FXML
    void reloadList() {
        orderTable.getColumns().get(0).setVisible(false);
        orderTable.getColumns().get(0).setVisible(true);
        orderTable.getItems().removeAll(orderTable.getItems());
        orderTable.setItems(getOrderList());
        titleColumn.setText("Hey");
        root.setVisible(false);

    }


    @Override
    public void onReloadList() {

        reloadList();
    }
}
