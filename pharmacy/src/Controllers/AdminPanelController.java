package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Obgect.Couriers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Couriers> couriersTableView;

    @FXML
    private TableColumn<Couriers, String> idColumn;

    @FXML
    private TableColumn<Couriers, String> nameColumn;

    @FXML
    private TableColumn<Couriers, String> surnameColumn;

    @FXML
    private TableColumn<Couriers, String> passwordColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    DBController dbController = new DBController();

    @FXML
    void initialize() throws SQLException {
        initData();

        addButton.setOnAction(event -> {
            try {
                MainController.showScene(addButton, "/samples/addDialogWindow.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateButton.setOnAction(event -> {
        });
    }

    private void initData() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Couriers, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Couriers, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Couriers, String>("surname"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Couriers, String>("password"));
        dbController.getDbConnection();
        couriersTableView.setItems(dbController.getCouriers());

    }
}