package Controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import Obgects.Const;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class CourierController {

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
    private static String nameCourier;
    private static String surnameCourier;
    private static String passwordCourier;

    @FXML
    void initialize() throws SQLException {
        nameCourier = courier.getCourierName();
        surnameCourier = courier.getCourierSurname();
        passwordCourier = courier.getCourierPassword();
        dbController.getDbConnection();
        String login = dbController.getUser(nameCourier, surnameCourier).getLogin();
        nameUser = dbController.getUserName(login);
        nameField.setText(nameUser);
        surnameUser = dbController.getUserSurname(login);
        surnameField.setText(surnameUser);
        adres = "город " + dbController.getUser(nameCourier, surnameCourier).getCity() +
                "\nул. " + dbController.getUser(nameCourier, surnameCourier).getStreet() +
                "\nдом " +  dbController.getUser(nameCourier, surnameCourier).getHouse();
        adresText.setText(adres);
        outputOrder();
        deleteOrderButton.setOnAction(event -> {
            try {
                checkStatement();
                deleteOrder();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
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

    private void checkStatement() throws IOException {
        Date date = new Date();
        String check = "---------- " + nameUser + " " + surnameUser + " ----------" +
                "\n\n" + orders + date + "---------\n\n\n";
        System.out.println(check);
        String fileName = nameUser + "_" + surnameUser + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            FileReader fileReader = new FileReader(fileName);
            char[] buf = new char[10000];
            fileReader.read(buf);
            for (char c : buf)
                check += c;
        }
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(check);
    }

    private void deleteOrder() throws SQLException {
        String id = "";
        String query = "select id from pharmacy.курьеры where имя = '" + nameCourier +
                        "' and фамилия = '" + surnameCourier +
                        "' and пароль = '" + passwordCourier +"'";
        dbController.statement = dbController.dbConnection.createStatement();
        dbController.resultSet = dbController.statement.executeQuery(query);
        while (dbController.resultSet.next()){
            id = dbController.resultSet.getString(1);
        }
        query = "DELETE FROM pharmacy." + Const.ORDER_TABLE + " WHERE " + Const.ORDER_ID_COURIERS + " = '" + id +"'";
        dbController.statement.executeUpdate(query);
        query = "UPDATE pharmacy.курьеры SET заказы = null WHERE имя = '" + nameCourier+ "' and фамилия = '" + surnameCourier +
                "' and пароль = '" + passwordCourier +"'";
        dbController.statement.executeUpdate(query);
        nameField.setText("");
        surnameField.setText("");
        adresText.setText("");
        textOrder.setText("Заказ выполнен.");
    }
}
