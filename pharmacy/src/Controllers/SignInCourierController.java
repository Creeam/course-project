package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInCourierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private Button signInButton;

    private static String name;
    private static String surname;
    private static String password;

    DBController dbController = new DBController();
    MainController mainController = new MainController();

    @FXML
    void initialize() throws SQLException {
        dbController.getDbConnection();
        signInButton.setOnAction(event -> {
            name = nameField.getText().trim();
            surname = surnameField.getText().trim();
            password = passwordField.getText().trim();
            if (signIn(name,surname,password)){
                try {
                    mainController.showScene(signInButton, "/Samples/courierWindow.fxml");
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

    public boolean signIn(String name, String surname, String password) {
        for(int i = 0; i < dbController.getCouriers().size(); i++){
            if (name.equals(dbController.getCouriers().get(i).getName()) &&
                surname.equals(dbController.getCouriers().get(i).getSurname()) &&
                password.equals(dbController.getCouriers().get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    public static String getCourierName() {
        return name;
    }

    public static String getCourierSurname() {
        return surname;
    }

    public static String getCourierPassword() {
        return password;
    }
}
