package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Obgect.Const;
import Obgect.Medicament;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShoppingBarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idMedicament;

    @FXML
    private TextField quantityMedicament;

    @FXML
    private TextField totalPrice;

    @FXML
    private Button buyButton;

    @FXML
    private Button paymentButton;

    DBController dbController = new DBController();


    @FXML
    void initialize() {

        paymentButton.setOnAction(event -> {
            totalPrice.clear();
            totalPrice.setText(String.valueOf(totalPrice(Integer.parseInt(quantityMedicament.getText().trim()))));
        });

        buyButton.setOnAction(event -> {
            try {
                dbController.getDbConnection();
                findingTheRemainder();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Stage primaryStage = (Stage) buyButton.getScene().getWindow();
            primaryStage.close();
        });

    }

    private double totalPrice(int insertQuantity){
        double total = 0;
        try {
            dbController.getDbConnection();
            Medicament medicament = dbController.getMedicament(idMedicament.getText());
            double price = Double.parseDouble(medicament.getPrice());
            int quantity = Integer.parseInt(medicament.getQuantity());
            if(insertQuantity > quantity){
                System.out.println("insert > quantity");
            } else {
                total = price * insertQuantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  total;
    }

    private void findingTheRemainder(){
        try {
            dbController.getDbConnection();
            Medicament medicament = dbController.getMedicament(idMedicament.getText());
            int quantity = Integer.parseInt(medicament.getQuantity());
            int remainder = quantity - Integer.parseInt(quantityMedicament.getText().trim());
            String r = String.valueOf(remainder);
            System.out.println(r);
            String query = "update pharmacy.каталог SET колличество = '" + r + "' WHERE (id = '" + idMedicament.getText().trim() + "')";
            try {
                dbController.statement = dbController.dbConnection.createStatement();
                dbController.statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
