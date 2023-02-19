package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.City;

public interface ICityDAO extends IBaseDAO<City>{

    City getCityByName(String name);
}
