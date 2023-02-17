package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.impl.CityDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CityService {

    public List<City> getCities() {
        List<City> cities = new CityDAOImpl().read();
        cities.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        return cities;
    }

    public Optional<City> getCityById(int id){
        return new CityDAOImpl().read().stream()
                .filter(x -> x.getId() == id)
                .findAny();
    }

    public Optional<City> getCityByName(String name){

        return new CityDAOImpl().read().stream()
                .filter(x -> x.getName().equals(name))
                .findAny();
    }

    public List<City> mapIdListToCity(List<Integer> list){
        List<City> cityList = new ArrayList<>();
        for(Integer i : list){
            City c = getCityById(i).orElse(new City());
            cityList.add(c);
        }
        return cityList;
    }
}
