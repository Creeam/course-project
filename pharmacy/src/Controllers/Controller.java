package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Obgect.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInAdminButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    private DBController dbController;
    private MainController controller;

    @FXML
    void initialize(){
        signInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    dbController = new DBController();
                    dbController.getDbConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (signIn(loginText, loginPassword)){
                    controller = new MainController();
                    try {
                        controller.showScene(signInButton, "/samples/catalog.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else
                System.out.println("Error");
        });


        signUpButton.setOnAction(event -> {
            try {
                MainController.showScene(signUpButton, "/samples/signUp.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        signInAdminButton.setOnAction(event -> {
            try {
                MainController.showScene(signInAdminButton, "/samples/signInAdmin.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean signIn(String login, String password){
        User user = dbController.getUserForSignIn(login, password);
        if(user.getLogin().equals(login) && user.getPassword().equals(password)){
            return true;
        } else {
            return false;
        }
    }
}