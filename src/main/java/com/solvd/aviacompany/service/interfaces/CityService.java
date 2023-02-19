package com.solvd.aviacompany.service.interfaces;

import com.solvd.aviacompany.hierarchy.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> getCities();

    Optional<City> getCityById(int id);

    Optional<City> getCityByName(String name);

    List<City> mapIndexListToCity(List<Integer> list);
}
