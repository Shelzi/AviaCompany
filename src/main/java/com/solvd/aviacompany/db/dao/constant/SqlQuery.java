package com.solvd.aviacompany.db.dao.constant;

public class SqlQuery {
    private SqlQuery() {
    }

    public static final String SQL_GET_PASSENGER_ID = "SELECT * FROM Passengers WHERE id = ?";
    public static final String SQL_INSERT_PASSENGER = "INSERT INTO Passengers(first_name, last_name) VALUES(?,?)";
    public static final String SQL_GET_ALL_PASSENGERS = "SELECT * FROM Passengers";
    public static final String SQL_UPDATE_PASSENGER = "UPDATE Passengers SET first_name=?, last_name=? WHERE id=?";
    public static final String SQL_GET_PASSENGER_BY_FIRST_NAME_AND_LAST_NAME = "SELECT * FROM Passengers " +
            "WHERE first_name = ? AND last_name = ?";
    public static final String SQL_GET_COUNTRY_ID = "SELECT * FROM Country WHERE id = ?";
    public static final String SQL_INSERT_COUNTRY = "INSERT INTO Country(name) VALUES(?)";
    public static final String SQL_GET_ALL_COUNTRIES = "SELECT * FROM Country";
    public static final String SQL_UPDATE_COUNTRY = "UPDATE Country SET name=? WHERE id=?";
    public static final String SQL_GET_COUNTRY_NAME = "SELECT * FROM Country WHERE name = ?";
}
