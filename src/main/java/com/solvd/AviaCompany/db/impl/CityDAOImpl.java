package com.solvd.AviaCompany.db.impl;

import com.solvd.AviaCompany.db.dao.ICityDAO;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.AviaCompany.db.tablecolumns.CityColumn.*;

public class CityDAOImpl extends JDBCConnectionManager implements ICityDAO {

    private static final Logger logger = LogManager.getLogger(CityDAOImpl.class);
    private static final String GET_CITY_ID = "SELECT * FROM City WHERE id = ?";
    private static final String INSERT_CITY = "INSERT INTO City(name, country_id) VALUES(?, ?)";
    private static final String GET_ALL_CITIES = "SELECT * FROM City";
    private static final String UPDATE_CITY = "UPDATE City SET name=?, country_id=? WHERE id=?";
    private static final String GET_CITY_NAME = "SELECT * FROM City WHERE name = ?";


    @Override
    public boolean create(City entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CITY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountryID());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                logger.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public List<City> read() {
        Connection connection = null;
        Statement statement = null;
        List<City> cityList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CITIES);
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt(ID.getColumn()));
                city.setName(resultSet.getString(NAME.getColumn()));
                city.setCountryID(resultSet.getInt(COUNTRY.getColumn()));
                cityList.add(city);
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        } finally {
            close(statement);
            close(connection);
        }
        return cityList;
    }

    @Override
    public City update(City entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CITY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountryID());
            preparedStatement.setInt(3, entity.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0){
                logger.warn("No rows were inserted");
                return null;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return entity;
    }

    @Override
    public City read(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_CITY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt(ID.getColumn()));
                city.setName(resultSet.getString(NAME.getColumn()));
                city.setCountryID(resultSet.getInt(COUNTRY.getColumn()));
                return city;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(City entity) {
        return false;
    }

    @Override
    public City getCityByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_CITY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt(ID.getColumn()));
                city.setName(resultSet.getString(NAME.getColumn()));
                city.setCountryID(resultSet.getInt(COUNTRY.getColumn()));
                return city;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }
}
