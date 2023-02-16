package com.solvd.AviaCompany.db.impl;

import com.solvd.AviaCompany.db.dao.ICountryDAO;
import com.solvd.AviaCompany.hierarchy.Country;
import com.solvd.AviaCompany.utils.connection.JDBCConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.AviaCompany.db.tablecolumns.CountryColumn.*;

public class CountryDAOImpl extends JDBCConnectionManager implements ICountryDAO {

    private static final Logger LOGGER = LogManager.getLogger(CountryDAOImpl.class);
    private static final String GET_COUNTRY_ID = "SELECT * FROM Country WHERE id = ?";
    private static final String INSERT_COUNTRY = "INSERT INTO Country(name) VALUES(?)";
    private static final String GET_ALL_COUNTRIES = "SELECT * FROM Country";
    private static final String UPDATE_COUNTRY = "UPDATE Country SET name=? WHERE id=?";

    @Override
    public boolean create(Country entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COUNTRY);
            preparedStatement.setString(1, entity.getName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                LOGGER.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public List<Country> read() {
        Connection connection = null;
        Statement statement = null;
        List<Country> countryList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_COUNTRIES);
            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt(ID.getColumn()));
                country.setName(resultSet.getString(NAME.getColumn()));
                countryList.add(country);
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement / Invalid field");
        } finally {
            close(statement);
            close(connection);
        }
        return countryList;
    }

    @Override
    public Country update(Country entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COUNTRY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0){
                LOGGER.warn("No rows were inserted");
                return entity;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return entity;
    }

    @Override
    public Country read(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_COUNTRY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt(ID.getColumn()));
                country.setName(resultSet.getString(NAME.getColumn()));
                return country;
            }
        } catch (SQLException e) {
            LOGGER.warn("Wrong statement  / Invalid field");
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
    public boolean delete(Country entity) {
        return false;
    }
}
