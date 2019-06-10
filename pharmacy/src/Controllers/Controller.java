package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import Obgects.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInAdminButton;
    @FXML
    private Button signInCourierButton;
    @FXML
    private TextField login_field;
    @FXML
    private PasswordField password_field;

    private DBController dbController;
    private MainController controller;
    private static String loginText;

    @FXML
    void initialize(){
        signInButton.setOnAction(event -> {
            loginText = login_field.getText().trim();
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
                        controller.showScene(signInButton, "/Samples/catalog.fxml");
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
            } else {
                try {
                    MainController.showModalScene(event, "/Samples/Error.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        signUpButton.setOnAction(event -> {
            try {
                MainController.showModalScene(event, "/Samples/signUp.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        signInAdminButton.setOnAction(event -> {
            try {
                MainController.showModalScene(event, "/Samples/signInAdmin.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signInCourierButton.setOnAction(event -> {
            try {
                MainController.showModalScene(event, "/Samples/signInCourier.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean signIn(String login, String password){
        User users = dbController.getUserForSignIn(login, password);
        if(users.getLogin().equals(login) && users.getPassword().equals(password)){
            System.out.println(users.getLogin() + " " + users.getPassword());
            return true;
        } else {
            return false;
        }
    }

    public String getLogin() {
        return loginText;
    }



}
