package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements BaseMapper<City> {
    @Override
    public City map(ResultSet resultSet) throws SQLException {
        return City.builder()
                .id(resultSet.getInt("city.id"))
                .name(resultSet.getString("city.name"))
                .country(new CountryMapper().map(resultSet))
                .build();
    }
}
