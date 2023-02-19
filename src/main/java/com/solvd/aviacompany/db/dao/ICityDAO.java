package com.solvd.AviaCompany.db.dao;

import com.solvd.AviaCompany.hierarchy.City;

public interface ICityDAO extends IBaseDAO<Integer, City>{

    City getCityByName(String name);
}
