package Obgects;

public class Couriers {
    private String id, name, surname, password, orders;

    public Couriers(String id, String name, String surname, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public Couriers(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public Couriers(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
