package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public static void showScene(Button button, String nameScene) throws IOException {

        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(MainController.class.getResource(nameScene));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void showModalScene(ActionEvent actionEvent, String nameScene) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(MainController.class.getResource(nameScene));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        primaryStage.show();
    }

    public static void showScene(Button button, String nameScene, int width, int height) {
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
        stage.setScene(new Scene(root, width, height));

        stage.showAndWait();
    }

}
