package com.solvd.aviacompany.db.dao.impl;

import com.solvd.aviacompany.db.dao.ICountryDAO;
import com.solvd.aviacompany.db.dao.constant.SqlQuery;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.dao.mapper.impl.CountryMapper;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.hierarchy.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryDaoImpl implements ICountryDAO {
    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final BaseMapper<Country> countryMapper = new CountryMapper();

    @Override
    public boolean create(Country entity) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_INSERT_COUNTRY)) {
            preparedStatement.setString(1, entity.getName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                logger.warn("No rows were inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return true;
    }

    @Override
    public List<Country> read() {
        List<Country> countryList = new ArrayList<>();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_UPDATE_PASSENGER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countryList.add(countryMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return countryList;
    }

    @Override
    public boolean update(Country country) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_UPDATE_COUNTRY)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getId());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
            return false;
        }
    }

    @Override
    public Optional<Country> read(int id) {
        Optional<Country> countryOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_COUNTRY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countryOptional = Optional.of(countryMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return countryOptional;
    }

    @Override
    public Optional<Country> getCountryByName(String name) {
        Optional<Country> countryOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_COUNTRY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countryOptional = Optional.of(countryMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return countryOptional;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Country entity) {
        return false;
    }
}
