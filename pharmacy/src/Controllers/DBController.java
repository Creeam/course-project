package Controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import Obgects.Const;
import Obgects.Couriers;
import Obgects.Medicament;
import Obgects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBController {

    protected String dbHost = "127.0.0.1";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "1234";
    protected String dbName = "pharmacy";
    protected String query;

    Connection dbConnection;
    Statement statement;
    ResultSet resultSet;

    
    // Соединение с базой данных
    public Connection getDbConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        statement = dbConnection.createStatement();
        return dbConnection;
    }


    //получение списка курьеров
    public ObservableList<Couriers> getCouriers(){
        query = "SELECT * FROM " + Const.COURIERS_TABLE;
        String id, name, surname, password;
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


    //получение конкретного курьера
    public Couriers getCourier(){
        query = "SELECT * FROM " + Const.COURIERS_TABLE + "WHERE ";
        String id = "", name = "", surname = "", password = "";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                password = resultSet.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Couriers(id, name, surname, password);
    }


    //добавление курьера
    public void addCouriers(Couriers couriers){
        query = "INSERT INTO " + Const.COURIERS_TABLE + " ("+ Const.COURIERS_NAME +", "+ Const.COURIERS_SURNAME +", "+
                Const.COURIERS_PASSWORD +") VALUES ('" +couriers.getName()+ "', '" + couriers.getSurname() + "', '" +
                couriers.getPassword() + "')";
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //удаление курьера
    public void removeCouriers(String id, String surname){
        query = "DELETE FROM "+ Const.COURIERS_TABLE +" WHERE id = '" + id + "' AND фамилия = '" + surname + "'";
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //получение пользователя для входа в программу
    public User getUserForSignIn(String login, String password) {
        String id = "", name = "", surname = "", phone = "", card = "", city = "", street = "", house = "", purchase = "";
        query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + " = '" + login + "' AND " + Const.USERS_PASSWORD + " = '" + password + "'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                phone = resultSet.getString(4);
                card = resultSet.getString(5);
                login = resultSet.getString(6);
                password = resultSet.getString(7);
                city = resultSet.getString(8);
                street = resultSet.getString(9);
                house = resultSet.getString(10);
                purchase = resultSet.getString(11);
                return new User(id, name, surname, phone, card, login, password, city, street, house, purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User("", "", "", "", "", "", "", "", "", "", "");
    }

    //получение пользователя
    public User getUser(String nameCourier, String surnameCourier) {
        String id = "", name = "", surname = "", phone = "", card = "", login = "", password = "",
                city = "", street = "", house = "", purchase = "";
        query = "select * from " + Const.USER_TABLE + " where логин = (select заказы from курьеры where имя = '" +
                nameCourier + "' and фамилия = '" + surnameCourier + "')";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                phone = resultSet.getString(4);
                card = resultSet.getString(5);
                login = resultSet.getString(6);
                password = resultSet.getString(7);
                city = resultSet.getString(8);
                street = resultSet.getString(9);
                house = resultSet.getString(10);
                purchase = resultSet.getString(11);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(id, name, surname, phone, card, login, password, city, street, house, purchase);
    }


    //получение списка пользователей
    public ObservableList<User> getUsers(){
        query = "SELECT * FROM " + Const.USER_TABLE;
        String id, name, surname, phone, card, login, password, city, street, house, purchase;
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                phone = resultSet.getString(4);
                card = resultSet.getString(5);
                login = resultSet.getString(6);
                password = resultSet.getString(7);
                city = resultSet.getString(8);
                street = resultSet.getString(9);
                house = resultSet.getString(10);
                purchase = resultSet.getString(11);
                users.add(new User(id, name, surname, phone, card, login, password, city, street, house, purchase));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    // Добавление пользователя
    public void signUpUser(User user){
        query = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_NAME + "," +
                Const.USERS_SURNAME + "," + Const.USER_PHONE + "," + Const.USER_CARD + "," +
                Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USERS_CITY + "," +
                Const.USERS_STREET + "," + Const.USERS_HOUSE + "," + Const.USERS_PURCHASES + ")" + "VALUES('" + user.getName() + "', '" + user.getSurname() +
                "' , '" + user.getPhone()+ "', '" + user.getCard() + "', '" + user.getLogin() + "', '" + user.getPassword() + "', '" +
                user.getCity() + "', '" + user.getStreet() + "', '" + user.getHouse() + "', '0')";
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //получение скидки пользователя
    public int getDiscount(String userLogin){
        int purchase = 0;
        int discount = 0;
        query = "SELECT " + Const.USERS_PURCHASES + " FROM pharmacy." + Const.USER_TABLE +
                " WHERE " + Const.USERS_LOGIN + " = '" + userLogin + "'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                purchase = resultSet.getInt(1);
            }
            if (purchase < 5)
                discount = 0;
            else if (purchase < 10)
                discount = 5;
            else if (purchase < 15)
                discount = 10;
            else if (purchase < 30)
                discount = 15;
            else
                discount = 30;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }


    //получение списка медикаментов
    public ObservableList<Medicament> getMedicaments(){
        query = "SELECT * FROM " + Const.MEDICAMENT_TABLE;
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

    //получение КОНКРЕТНОГО медикамента
    public Medicament getMedicament(String id){
        String name = "", country = "", quantity = "", price = "";
        query = "SELECT * FROM " + Const.MEDICAMENT_TABLE + " WHERE id = '" + id +"'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                name = resultSet.getString(2);
                country = resultSet.getString(3);
                quantity = resultSet.getString(4);
                price = resultSet.getString(5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Medicament(id, name, country, quantity, price);
    }

    public ArrayList<HashMap> order (ArrayList<HashMap> map) {
        String login = getUser(SignInCourierController.getCourierName(), SignInCourierController.getCourierSurname()).getLogin();
        query = "SELECT * FROM "+ Const.ORDER_TABLE +" WHERE логин_покупателя = '"+ login +"'";
        HashMap<String, String> temp = new HashMap<>();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                temp.put("Имя", getUserName(resultSet.getString(2)));
                temp.put("Фамилия", getUserSurname(resultSet.getString(2)));
                temp.put("Покупка", getItem(resultSet.getInt(3)));
                temp.put("Количество", resultSet.getString(4));
                temp.put("Цена", resultSet.getString(5));
                map.add(new HashMap(temp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getUserName(String login) {
        String name = "";
        Statement statement;
        ResultSet resultSet;
        query = "SELECT " + Const.USERS_NAME + " FROM " + Const.USER_TABLE + " WHERE логин = '" + login +"'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                name = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getUserSurname(String login) {
        String surname = "";
        Statement statement;
        ResultSet resultSet;
        query = "SELECT " + Const.USERS_SURNAME + " FROM " + Const.USER_TABLE + " WHERE логин = '" + login +"'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                surname = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surname;
    }

    public String getItem(int id) {
        String item = "";
        Statement statement;
        ResultSet resultSet;
        query = "SELECT " + Const.MEDICAMENT_NAME + " FROM " + Const.MEDICAMENT_TABLE + " WHERE id = '" + id +"'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                item = resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
