package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.Flight;
import com.solvd.aviacompany.service.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public class CityServiceImpl implements CityService {

    private final List<City> cities;

    public CityServiceImpl() {
        List<Flight> flights = new FlightServiceImpl().getFlights();
        Set<City> citiesSet = new HashSet<>();
        for(Flight f : flights) {
            citiesSet.add(f.getDeparture());
            citiesSet.add(f.getDestination());
        }
        List<City> cityList = new ArrayList<>(citiesSet.stream().toList());
        cityList.sort(Comparator.comparingInt(City::getId));
        cities = cityList;
    }

    public List<City> getCities() {
        return cities;
    }

    public Optional<City> getCityById(int id){
        return getCities().stream().filter((x) -> x.getId() == id).findAny();
        //return Optional.ofNullable(iCityDAO.read(id));
    }

    public Optional<City> getCityByName(String name){
        return getCities().stream().filter((x) -> x.getName().equals(name)).findAny();
        // return Optional.ofNullable(iCityDAO.getCityByName(name));
    }

    public List<City> mapIndexListToCity(List<Integer> list){
        List<City> cityList = new ArrayList<>();
        for(Integer i : list){
            City c = getCityByIndex(i);
            cityList.add(c);
        }
        return cityList;
    }

    public int getIndexOfCityByName(String name){
        List<City> cities = getCities();
        int res = -1;
        for(City c : cities){
            if(c.getName().equals(name)){
                res = cities.indexOf(c);
                break;
            }
        }
        return res;
    }

    public City getCityByIndex(int index){
        return cities.get(index);
    }
}
