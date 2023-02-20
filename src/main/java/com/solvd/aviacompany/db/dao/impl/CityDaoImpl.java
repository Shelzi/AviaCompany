package com.solvd.aviacompany.db.dao.impl;

import com.solvd.aviacompany.db.dao.ICityDao;
import com.solvd.aviacompany.db.dao.constant.SqlQuery;
import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.db.dao.mapper.impl.CityMapper;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.hierarchy.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements ICityDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final BaseMapper<City> cityMapper = new CityMapper();

    @Override
    public boolean create(City city) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_INSERT_CITY)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry().getId());
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
    public List<City> read() {
        List<City> cityList = new ArrayList<>();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_ALL_CITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(cityMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return cityList;
    }

    @Override
    public boolean update(City city) {
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_UPDATE_CITY)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry().getId());
            preparedStatement.setInt(3, city.getId());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
            return false;
        }
    }

    @Override
    public Optional<City> read(int id) {
        Optional<City> cityOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_CITY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityOptional = Optional.of(cityMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return cityOptional;
    }

    @Override
    public Optional<City> getCityByName(String name) {
        Optional<City> cityOptional = Optional.empty();
        try (Connection c = pool.takeConnection();
             PreparedStatement preparedStatement = c.prepareStatement(SqlQuery.SQL_GET_CITY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cityOptional = Optional.of(cityMapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Wrong statement / Invalid field");
        }
        return cityOptional;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(City city) {
        return false;
    }
}
