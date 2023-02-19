package com.solvd.aviacompany.db.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface BaseMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
