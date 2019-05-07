package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public static void showScene(Button button, String nameScene) throws IOException {
        /*button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource(nameScene));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();*/

        Stage primaryStage = new Stage();
        button.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(MainController.class.getResource(nameScene));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void showScene(Button button, String nameScene, int width, int haight) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource(nameScene));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, width, haight));

        stage.showAndWait();
    }

}
