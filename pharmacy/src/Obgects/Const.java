package Obgects;

public class Const {


    //таблица пользователи
    public static final String USER_TABLE = "пользователи";
    public static final String USERS_ID = "id_пользователя";
    public static final String USERS_NAME = "имя";
    public static final String USERS_SURNAME = "фамилия";
    public static final String USER_PHONE = "телефон";
    public static final String USER_CARD = "номер_кредитной_карты";
    public static final String USERS_LOGIN = "логин";
    public static final String USERS_PASSWORD = "пароль";
    public static final String USERS_CITY = "город";
    public static final String USERS_STREET = "улица";
    public static final String USERS_HOUSE = "дом";
    public static final String USERS_PURCHASES = "число_покупок";

    //таблица курьеры
    public static final String COURIERS_TABLE = "курьеры";
    public static final String COURIERS_ID = "id";
    public static final String COURIERS_NAME = "имя";
    public static final String COURIERS_SURNAME = "фамилия";
    public static final String COURIERS_PASSWORD = "пароль";
    public static final String COURIERS_ORDERS = "заказы";

    //таблица медикоментов
    public static final String MEDICAMENT_TABLE = "каталог";
    public static final String MEDICAMENT_ID = "id";
    public static final String MEDICAMENT_NAME = "название";
    public static final String MEDICAMENT_COUNTRY = "страна";
    public static final String MEDICAMENT_QUANTITY = "колличество";
    public static final String MEDICAMENT_PRICE = "цена(шт.)";

    //таблица заказов
    public static final String ORDER_TABLE = "заказы";
    public static final String ORDER_ID = "id";
    public static final String ORDER_LOGIN_USER = "логин_покупателя";
    public static final String ORDER_ID_MEDICAMENT = "id_товара";
    public static final String ORDER_MEDICAMENT_QUANTITY = "количество";
    public static final String ORDER_PRICE = "цена";
    public static final String ORDER_ID_COURIERS = "id_курьера";


}
