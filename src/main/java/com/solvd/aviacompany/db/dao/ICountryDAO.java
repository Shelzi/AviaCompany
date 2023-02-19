package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Country;

public interface ICountryDAO extends IBaseDAO<Integer, Country> {
    Country getCountryByName(String name);
}
