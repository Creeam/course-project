package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErrorWindowController {

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        okButton.setOnAction(event -> {
            Stage errorStage = (Stage) okButton.getScene().getWindow();
            errorStage.close();
        });
    }
}
