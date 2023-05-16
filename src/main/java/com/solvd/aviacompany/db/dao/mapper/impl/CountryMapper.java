package com.solvd.aviacompany.db.dao.mapper.impl;

import com.solvd.aviacompany.db.dao.mapper.BaseMapper;
import com.solvd.aviacompany.hierarchy.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements BaseMapper<Country> {
    @Override
    public Country map(ResultSet resultSet) throws SQLException {
        return Country.builder()
                .id(resultSet.getInt("country.id"))
                .name(resultSet.getString("country.name"))
                .build();
    }
}
