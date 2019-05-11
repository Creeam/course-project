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
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField phone_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField card_field;

    @FXML
    private TextField city_field;

    @FXML
    private TextField street_field;

    @FXML
    private TextField house_field;

    @FXML
    private Button signUpButton;

    @FXML

    void initialize() {
        signUpButton.setOnAction(event -> {
            signUpNewUser();
            Stage primaryStage = (Stage) signUpButton.getScene().getWindow();
            primaryStage.close();
        });
    }

    private void signUpNewUser() {
        DBController dbController = new DBController();
        try {
            dbController.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String name = name_field.getText();
        String surname = surname_field.getText();
        String phone = phone_field.getText();
        String card = card_field.getText();
        String login = login_field.getText();
        String password = password_field.getText();
        String city = city_field.getText();
        String street = street_field.getText();
        String house = house_field.getText();
        String purchases = "0";

        User user = new User(name, surname, phone, card, login, password, city, street, house, purchases);

        dbController.signUpUser(user);
    }
}
