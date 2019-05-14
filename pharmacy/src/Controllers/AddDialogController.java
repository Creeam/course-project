package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Obgects.Couriers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AddDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button addButton;

    @FXML
    private PasswordField couriersPassword;

    @FXML
    void initialize() {

        addButton.setOnAction(event -> {
            addCouriers();
        });

    }

    private void addCouriers(){
        DBController dbController = new DBController();
        try {
            dbController.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String name = nameField.getText();
        String surname= surnameField.getText();
        String password = couriersPassword.getText();

        Couriers couriers = new Couriers(name, surname, password);
        dbController.addCouriers(couriers);

    }
}
