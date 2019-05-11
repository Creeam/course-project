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
    private TextField totalPriceField;

    @FXML
    private TextField discountField;

    @FXML
    private Button buyButton;

    @FXML
    private Button paymentButton;

    DBController dbController = new DBController();
    Controller controller = new Controller();
    String login;


    @FXML
    void initialize() throws SQLException {
        login = controller.getLogin();
        dbController.getDbConnection();
        discountField.setText(String.valueOf(dbController.getDiscount(login)));

        paymentButton.setOnAction(event -> {
            totalPriceField.clear();
            totalPriceField.setText(String.valueOf(totalPrice(Integer.parseInt(quantityMedicament.getText().trim()))));
        });

        buyButton.setOnAction(event -> {
            Stage primaryStage = (Stage) buyButton.getScene().getWindow();
            checkout(login);
            primaryStage.close();
        });

    }

    private double totalPrice(int insertQuantity){
        double total = 0;
        Medicament medicament = dbController.getMedicament(idMedicament.getText());
        double price = Double.parseDouble(medicament.getPrice());
        int quantity = Integer.parseInt(medicament.getQuantity());
        if(insertQuantity > quantity){
            System.out.println("insert > quantity");
        } else {
            total = price * insertQuantity;
        }
        double discount = total / 100 * dbController.getDiscount(login);
        total -= discount;
        return  total;
    }

    private void checkout(String userLogin){
        int purchase = 0;
        String query = "SELECT " + Const.USERS_PURCHASES + " FROM pharmacy." + Const.USER_TABLE +
                " WHERE " + Const.USERS_LOGIN + " = '" + userLogin + "'";
        try {
            dbController.statement = dbController.dbConnection.createStatement();
            dbController.resultSet = dbController.statement.executeQuery(query);
            while (dbController.resultSet.next()){
                purchase = dbController.resultSet.getInt(1);
            }
            purchase = purchase + 1;
            query = "UPDATE pharmacy." + Const.USER_TABLE + " SET " + Const.USERS_PURCHASES + " = '" + purchase
                    + "' WHERE логин = '" + userLogin + "';";
            dbController.statement.executeUpdate(query);
            findingTheRemainder();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void findingTheRemainder(){
        Medicament medicament = dbController.getMedicament(idMedicament.getText());
        int quantity = Integer.parseInt(medicament.getQuantity());
        int remainder = quantity - Integer.parseInt(quantityMedicament.getText().trim());
        String r = String.valueOf(remainder);
        String query = "update pharmacy." + Const.MEDICAMENT_TABLE + " SET " + Const.MEDICAMENT_QUANTITY + " = '" + r
                + "' WHERE (id = '" + idMedicament.getText().trim() + "')";
        try {
            dbController.statement = dbController.dbConnection.createStatement();
            dbController.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
