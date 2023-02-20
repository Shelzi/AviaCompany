package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.City;

import java.util.Optional;

public interface ICityDao extends IBaseDao<City> {
    Optional<City> getCityByName(String name);
}
