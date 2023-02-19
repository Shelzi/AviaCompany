package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.hierarchy.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements BaseMapper<Country> {
    @Override
    public Country map(ResultSet resultSet) throws SQLException {
        return new Country(
                resultSet.getInt("country.id"),
                resultSet.getString("country.name"));
    }
}
