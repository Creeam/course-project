package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInAdminController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adminLoginFIeld;

    @FXML
    private PasswordField adminPassworField;

    @FXML
    private Button sigInButton;


    @FXML
    void initialize() {
        sigInButton.setOnAction(event -> {
            String login = adminLoginFIeld.getText().trim();
            String password = adminPassworField.getText().trim();
            if (signIn(login, password)){
                try {
                    MainController.showScene(sigInButton, "/Samples/adminPanel.fxml");
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
