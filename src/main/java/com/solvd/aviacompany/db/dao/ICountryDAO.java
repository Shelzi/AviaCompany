package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Country;

import java.util.Optional;

public interface ICountryDAO extends IBaseDAO<Country> {
    Optional<Country> getCountryByName(String name);
}
