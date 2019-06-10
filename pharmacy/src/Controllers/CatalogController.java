package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import Obgects.Medicament;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CatalogController {

    @FXML
    private TableView<Medicament> catalogTableView;
    @FXML
    private TableColumn<Medicament, String> idColumn;
    @FXML
    private TableColumn<Medicament, String> nameColumn;
    @FXML
    private TableColumn<Medicament, String> countryColumn;
    @FXML
    private TableColumn<Medicament, String> quantityColumn;
    @FXML
    private TableColumn<Medicament, String> priceColumn;
    @FXML
    private Button buyButton;
    @FXML
    private Button updateButton;

    DBController dbController = new DBController();

    @FXML
    void initialize() throws SQLException {
        dbController.getDbConnection();
        initData();
        buyButton.setOnAction(event -> {
            try {
                MainController.showModalScene(event, "/Samples/shoppingBar.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        updateButton.setOnAction(event -> initData());
    }

    public void initData() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Medicament, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Medicament, String >("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Medicament, String>("country"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Medicament, String>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Medicament, String>("price"));
        catalogTableView.setItems(dbController.getMedicaments());
    }
}