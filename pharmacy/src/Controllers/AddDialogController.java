package Controllers;

import java.sql.SQLException;
import Obgects.Couriers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDialogController {

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
            Stage errorStage = (Stage) addButton.getScene().getWindow();
            errorStage.close();
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
