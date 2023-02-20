package com.solvd.aviacompany.db.dao.constant;

public class SqlQuery {
    private SqlQuery() {
    }

    public static final String SQL_GET_PASSENGER_ID = "SELECT * FROM Passengers WHERE id = ?";
    public static final String SQL_INSERT_PASSENGER = "INSERT INTO Passengers(first_name, last_name) VALUES(?,?)";
    public static final String SQL_GET_ALL_PASSENGERS = "SELECT * FROM Passengers";
    public static final String SQL_UPDATE_PASSENGER = "UPDATE Passengers SET first_name=?, last_name=? WHERE id=?";
    public static final String SQL_GET_PASSENGER_BY_FIRST_NAME_AND_LAST_NAME =
            "SELECT * FROM Passengers " +
                    "WHERE first_name = ? AND last_name = ?";
    public static final String SQL_GET_COUNTRY_ID = "SELECT * FROM Country WHERE id = ?";
    public static final String SQL_INSERT_COUNTRY = "INSERT INTO Country VALUES(?, ?)";
    public static final String SQL_GET_ALL_COUNTRIES = "SELECT * FROM Country";
    public static final String SQL_UPDATE_COUNTRY = "UPDATE Country SET name=? WHERE id=?";
    public static final String SQL_GET_COUNTRY_NAME = "SELECT * FROM Country WHERE name = ?";
    public static final String SQL_GET_ALL_FLIGHTS =
            "SELECT * FROM flights " +
                    "JOIN city a ON flights.dep_city_id = a.id " +
                    "JOIN city b ON flights.dest_city_id = b.id " +
                    "JOIN country a_country ON a.country_id = a_country.id " +
                    "JOIN country b_country ON b.country_id = b_country.id";
    public static final String SQL_INSERT_FLIGHT = "INSERT INTO Flights(dep_city_id, dest_city_id, cost, distance)" +
            " VALUES(?,?,?,?)";
    public static final String SQL_GET_FLIGHT_BY_ID = SQL_GET_ALL_FLIGHTS + " WHERE f.id = ?";
    /*    public static final String SQL_GET_CITY_ID = "SELECT ci.id, ci.name, co.id as country_id, co.name as country_name " +
                "FROM City ci " +
                "LEFT JOIN country co ON ci.country_id = co.id WHERE ci.id = ? group by ci.id";*/
    public static final String SQL_GET_CITY_ID = """
            SELECT * FROM city 
            JOIN country ON city.country_id = country.id 
            WHERE city.id = ?
            """;

    public static final String SQL_INSERT_CITY = "INSERT INTO City(name, country_id) VALUES(?, ?)";
    /*public static final String SQL_GET_ALL_CITIES = "SELECT ci.id as city_id, ci.name as city_name, ci.country_id as country_id, " +
            "co.name as country_name FROM City ci LEFT JOIN Country co ON ci.country_id = co.id";
    */

    public static final String SQL_GET_ALL_CITIES = "SELECT * FROM city JOIN country ON city.country_id = country.id";
    public static final String SQL_UPDATE_CITY = "UPDATE City SET name=?, country_id=? WHERE id=?";
    /*    public static final String SQL_GET_CITY_NAME = "SELECT ci.id, ci.name, co.id as country_id, co.name as country_name " +
                "FROM City ci " +
                "LEFT JOIN country co ON ci.country_id = co.id WHERE ci.name = ? group by ci.id";
        */
    public static final String SQL_GET_CITY_NAME = "SELECT * FROM city JOIN country ON city.country_id = country.id WHERE city.name = ? ";
    public static final String SQL_INSERT_TICKET = "INSERT INTO Tickets(passengers_id, flights_id) VALUES(?, ?)";
    public static final String SQL_GET_ALL_TICKETS = """
            SELECT * FROM tickets 
            LEFT JOIN flights ON tickets.flights_id = flights.id 
            LEFT JOIN city a ON flights.dep_city_id = a.id 
            LEFT JOIN country a_country ON a.country_id = a_country.id 
            LEFT JOIN city b ON flights.dest_city_id = b.id 
            LEFT JOIN country b_country ON b.country_id = b_country.id
            LEFT JOIN passengers ON tickets.passengers_id = passengers.id
            """;


    public static final String SQL_GET_FLIGHT_BY_DEP_DEST =
            "SELECT * FROM flights " +
                    "JOIN city a ON flights.dep_city_id = a.id " +
                    "JOIN city b ON flights.dest_city_id = b.id " +
                    "JOIN country a_country ON a.country_id = a_country.id " +
                    "JOIN country b_country ON b.country_id = b_country.id " +
                    "WHERE flights.dep_city_id = ? AND flights.dest_city_id = ? AND flights.cost = ? " +
                    "AND flights.distance = ?";

    public static final String SQL_GET_AUTO_INCREMENT = """
                    SELECT `AUTO_INCREMENT`
                    FROM  INFORMATION_SCHEMA.TABLES
                    WHERE TABLE_SCHEMA = 'AviaCompany'
                    AND   TABLE_NAME   = 'tickets';
                    """;

}
