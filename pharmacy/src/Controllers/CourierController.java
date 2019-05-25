package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class CourierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private Text adresText;

    @FXML
    private Text textOrder;

    @FXML
    private Button deleteOrderButton;

    private DBController dbController = new DBController();
    private SignInCourierController courier = new SignInCourierController();
    private static String adres;
    private static String orders;
    private static String nameUser;
    private static String surnameUser;

    @FXML
    void initialize() {
        try {
            dbController.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String login = dbController.getUser(courier.getCourierName(), courier.getCourierSurname()).getLogin();
        nameUser = dbController.getUserName(login);
        nameField.setText(nameUser);
        surnameUser = dbController.getUserSurname(login);
        surnameField.setText(surnameUser);
        adres = "город " + dbController.getUser(courier.getCourierName(), courier.getCourierSurname()).getCity() +
                "\nул. " + dbController.getUser(courier.getCourierName(), courier.getCourierSurname()).getStreet() +
                "\nдом " +  dbController.getUser(courier.getCourierName(), courier.getCourierSurname()).getHouse();
        adresText.setText(adres);
        outputOrder();
        deleteOrderButton.setOnAction(event -> {
            checkStatement();
        });
    }


    public void outputOrder() {
        ArrayList<HashMap> order =  new ArrayList<>();
        order = dbController.order(order);
        orders = "";
        for (int i = 0; i < order.size(); ++i) {
           orders += "Препарат: " + order.get(i).get("Препарат") + "" +
                    "\nКоличество: " + order.get(i).get("Количество") + " шт." +
                    "\nЦена: " + order.get(i).get("Цена") + " руб.\n\n";
        }
        textOrder.setText(orders);
    }

    private void checkStatement(){
        String check = "---------- " + nameUser + " " + surnameUser + " ----------" +
                        "\n\n" + orders +
                        "------------------------------";
        System.out.println(check);
        try(FileWriter fileWriter = new FileWriter(nameUser + "_" + surnameUser + ".txt")){
            fileWriter.write(check);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}