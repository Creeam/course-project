package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idField;

    @FXML
    private TextField surnameField;

    @FXML
    private Button removeButton;

    DBController dbController = new DBController();

    @FXML
    void initialize() {

        removeButton.setOnAction(event -> {
            try {
                dbController.getDbConnection();
                dbController.removeCouriers(idField.getText().trim(), surnameField.getText().trim());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }
}
