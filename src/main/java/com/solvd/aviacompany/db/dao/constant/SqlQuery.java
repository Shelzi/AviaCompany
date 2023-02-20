package com.solvd.aviacompany.db.dao.constant;

public class SqlQuery {
    public SqlQuery() {
    }

    public static final String SQL_GET_PASSENGER_ID = "SELECT * FROM Passengers WHERE id = ?";
    public static final String SQL_INSERT_PASSENGER = "INSERT INTO Passengers(first_name, last_name) VALUES(?,?)";
    public static final String SQL_GET_ALL_PASSENGERS = "SELECT * FROM Passengers";
    public static final String SQL_UPDATE_PASSENGER = "UPDATE Passengers SET first_name=?, last_name=? WHERE id=?";
    public static final String SQL_GET_PASSENGER_BY_FIRST_NAME_AND_LAST_NAME = "SELECT * FROM Passengers " +
            "WHERE first_name = ? AND last_name = ?";
    public static final String SQL_GET_COUNTRY_ID = "SELECT * FROM Country WHERE id = ?";
    public static final String SQL_INSERT_COUNTRY = "INSERT INTO Country VALUES(?, ?)";
    public static final String SQL_GET_ALL_COUNTRIES = "SELECT * FROM Country";
    public static final String SQL_UPDATE_COUNTRY = "UPDATE Country SET name=? WHERE id=?";
    public static final String SQL_GET_COUNTRY_NAME = "SELECT * FROM Country WHERE name = ?";
    public static final String SQL_GET_ALL_FLIGHTS = "SELECT * FROM flights " +
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
    public static final String SQL_GET_ALL_TICKETS = "SELECT p.id as passengers_id, p.first_name, p.last_name, " +
            "f.id as flights_id, f.cost, f.distance, dep_city.name as dep_name, dep_city.id as dep_id, " +
            "dest_city.name as dest_name, dest_city.id as dest_id, " +
            "dep_co.id as dep_country_id, dep_co.name as dep_country_name,  " +
            "dest_co.id as dest_country_id, dest_co.name as dest_country_name " +
            "FROM tickets t LEFT JOIN passengers p ON t.passengers_id = p.id " +
            "LEFT JOIN flights f ON t.flights_id = f.id " +
            "LEFT JOIN city dep_city ON f.dep_city_id = dep_city.id " +
            "LEFT JOIN city dest_city ON f.dest_city_id = dest_city.id " +
            "LEFT JOIN country dep_co ON dep_city.country_id = dep_co.id " +
            "LEFT JOIN country dest_co ON dest_city.country_id = dest_co.id";

}
