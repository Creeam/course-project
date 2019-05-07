package Controllers;

import java.sql.*;

import Obgect.Const;
import Obgect.Couriers;
import Obgect.Medicament;
import Obgect.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBController {
    protected String dbHost = "127.0.0.1";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "1234";
    protected String dbName = "pharmacy";
    protected String insert;

    Connection dbConnection;
    Statement statement;
    ResultSet resultSet;

    // Соединение с базой данных
    public Connection getDbConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        System.out.println("Connection done");
        statement = dbConnection.createStatement();
        return dbConnection;
    }

    // Добавление пользователя
    public void signUpUser(User user){
        insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," +
                Const.USERS_SURNAME + "," + Const.USER_PHONE + "," + Const.USER_CARD + "," +
                Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USERS_CITY + "," +
                Const.USERS_STREET + "," + Const.USERS_HOUSE + ")" + "VALUES('" + user.getName() + "', '" + user.getSurname() +
                "' , '" + user.getPhone()+ "', '" + user.getCard() + "', '" + user.getLogin() + "', '" + user.getPassword() + "', '" +
                user.getCity() + "', '" + user.getStreet() + "', '" + user.getHouse() + "')";
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //добавление курьера
    public void addCouriers(Couriers couriers){
        insert = "INSERT INTO" + Const.COURIERS_TABLE + "(" + Const.COURIERS_ID + "," + Const.COURIERS_NAME +
                "," + Const.COURIERS_SURNAME + "," + Const.COURIERS_PASSWORD + ") VALUES ('" + couriers.getId() +
                "', '" + couriers.getName() + "', '" + couriers.getSurname() + "', '" + couriers.getPassword() + ",)";

        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //получение пользователя для входа в программу
    public User getUserForSignIn(String login, String password) {

        String name = "", surname = "", phone = "", card = "", city = "", street = "", house = "";
        String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + " = '" + login + "' AND " + Const.USERS_PASSWORD + " = '" + password + "'";

        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                phone = resultSet.getString(4);
                card = resultSet.getString(5);
                login = resultSet.getString(6);
                password = resultSet.getString(7);
                city = resultSet.getString(8);
                street = resultSet.getString(9);
                house = resultSet.getString(10);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(name, surname, phone, card, login, password, city, street, house);
    }

    //получение списка пользователей
    public ObservableList<User> getUser(){
        String query = "SELECT * FROM " + Const.USER_TABLE;
        String name = "", surname = "", phone = "", card = "", login = "", password = "", city = "", street = "", house = "";
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                phone = resultSet.getString(4);
                card = resultSet.getString(5);
                login = resultSet.getString(6);
                password = resultSet.getString(7);
                city = resultSet.getString(8);
                street = resultSet.getString(9);
                house = resultSet.getString(10);
                users.add(new User(name, surname, phone, card, login, password, city, street, house));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    //получение списка курьеров
    public ObservableList<Couriers> getCouriers(){
        String query = "SELECT * FROM " + Const.COURIERS_TABLE;
        String id = "", name = "", surname = "", password = "";
        ObservableList<Couriers> couriers = FXCollections.observableArrayList();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                password = resultSet.getString(4);
                couriers.add(new Couriers(id, name, surname, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couriers;
    }

    //получение списка медикаментов
    public ObservableList<Medicament> getMedicaments(){
        String query = "SELECT * FROM " + Const.MEDICAMENT_TABLE;
        String id = "", name = "", country = "", quantity = "", price = "";
        ObservableList<Medicament> medicaments = FXCollections.observableArrayList();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                country = resultSet.getString(3);
                quantity = resultSet.getString(4);
                price = resultSet.getString(5);
                medicaments.add(new Medicament(id, name, country, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }

}