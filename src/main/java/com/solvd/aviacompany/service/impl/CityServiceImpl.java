package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.ICityDao;
import com.solvd.aviacompany.db.dao.impl.CityDaoImpl;
import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.service.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CityServiceImpl implements CityService {
    public static final ICityDao iCityDao = new CityDaoImpl();

    @Override
    public List<City> getCities() {
        return iCityDao.read();
    }

    public Optional<City> getCityById(int id) {
        //return getCities().stream().filter((x) -> x.getId() == id).findAny();
        return iCityDao.read(id);
    }

    public Optional<City> getCityByName(String name) {
        //return getCities().stream().filter((x) -> x.getName().equals(name)).findAny();
        return iCityDao.getCityByName(name);
    }

    public List<City> mapIndexListToCity(List<Integer> list, Map<Integer, City> cityIndexToNameMap) {
        List<City> cityList = new ArrayList<>();

        for (Integer i : list) {
            City c = cityIndexToNameMap.get(i);
            cityList.add(c);
        }
        return cityList;
    }

    public int getIndexOfCityByName(String name) {
        List<City> cities = getCities();
        int res = -1;
        for (City c : cities) {
            if (c.getName().equals(name)) {
                res = cities.indexOf(c);
                break;
            }
        }
        return res;
    }

    public City getCityByIndex(int index) {
        return iCityDao.read(index).get();
    }
}
