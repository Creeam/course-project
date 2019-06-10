package Controllers;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RemoveDialogController {

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
