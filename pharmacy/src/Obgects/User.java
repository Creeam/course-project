package Obgects;

public class User {
    private String id, name, surname, phone, card, login, password, city, street, house, purchase;


    public User(String id ,String name, String surname, String phone, String card, String login,
                String password, String city, String street, String house, String purchase) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.card = card;
        this.login = login;
        this.password = password;
        this.city = city;
        this.street = street;
        this.house = house;
        this.purchase = purchase;
    }

    public User(String name, String surname, String phone, String card, String login,
                String password, String city, String street, String house, String purchase) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.card = card;
        this.login = login;
        this.password = password;
        this.city = city;
        this.street = street;
        this.house = house;
        this.purchase = purchase;
    }


    public User () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPurchase() { return purchase; }

    public void setPurchase(String purchase) { this.purchase = purchase; }

}
