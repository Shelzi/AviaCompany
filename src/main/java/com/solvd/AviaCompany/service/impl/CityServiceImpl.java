package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.dao.ICityDAO;
import com.solvd.AviaCompany.db.impl.CityDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.service.interfaces.CityService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CityServiceImpl implements CityService {

    private ICityDAO iCityDAO;

    public CityServiceImpl() {
        this.iCityDAO = new CityDAOImpl();
    }

    public List<City> getCities() {
        List<City> cityList = iCityDAO.read();
        cityList.sort(Comparator.comparingInt(City::getId));
        return cityList;
    }

    public Optional<City> getCityById(int id){
        return Optional.ofNullable(iCityDAO.read(id));
    }

    public Optional<City> getCityByName(String name){
        return Optional.ofNullable(iCityDAO.getCityByName(name));
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
