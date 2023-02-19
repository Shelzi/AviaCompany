package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.City;

public interface ICityDAO extends IBaseDAO<Integer, City>{

    City getCityByName(String name);
}
