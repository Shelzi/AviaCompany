package com.solvd.AviaCompany.service.interfaces;

import com.solvd.AviaCompany.hierarchy.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> getCities();

    Optional<City> getCityById(int id);

    Optional<City> getCityByName(String name);

    List<City> mapIdListToCity(List<Integer> list);
}
