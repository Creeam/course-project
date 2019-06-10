package Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInAdminController {

    @FXML
    private TextField adminLoginField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private Button sigInButton;

    @FXML
    void initialize() {
        sigInButton.setOnAction(event -> {
            String login = adminLoginField.getText().trim();
            String password = adminPasswordField.getText().trim();
            if (signIn(login, password)){
                try {
                    MainController.showScene(sigInButton, "/Samples/adminPanel.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MainController.showModalScene(event, "/Samples/Error.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static boolean signIn(String login, String password){
        if (login.equals("admin") && password.equals("admin"))
            return true;
        else
            return false;
    }
}
