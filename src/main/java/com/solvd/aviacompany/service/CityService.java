package com.solvd.aviacompany.service;

import com.solvd.aviacompany.hierarchy.City;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CityService {
    List<City> getCities();

    Optional<City> getCityById(int id);

    Optional<City> getCityByName(String name);

    List<City> mapIndexListToCity(List<Integer> list, Map<Integer, City> indexToCityMap);
}
