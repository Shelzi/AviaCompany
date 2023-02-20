package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Country;

import java.util.Optional;

public interface ICountryDao extends IBaseDao<Country> {
    Optional<Country> getCountryByName(String name);
}
