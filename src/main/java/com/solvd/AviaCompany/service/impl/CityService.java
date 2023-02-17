package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.impl.CityDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CityService {
    private List<City> cities;

    {
        cities = new CityDAOImpl().read();
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                int res;
                if (o1.getId() == o2.getId())
                    res = 0;
                else if (o1.getId() < o2.getId())
                    res = 1;
                else
                    res = -1;
                return res;
            }
        });
    }

    public List<City> getCities() {
        return cities;
    }

    public Optional<City> getCityById(int id){
        return cities.stream().filter((x) -> x.getId() == id).findAny();
    }

    public Optional<City> getCityByName(String name){
        return cities.stream().filter((x) -> x.getName().equals(name)).findAny();
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
