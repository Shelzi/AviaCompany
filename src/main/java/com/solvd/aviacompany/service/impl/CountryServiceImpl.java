package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.ICountryDao;
import com.solvd.aviacompany.db.dao.impl.CountryDaoImpl;

import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger();
    private final ICountryDao iCountryDao = new CountryDaoImpl();

    public List<Country> getAllCountries() {
        List<Country> countryList = iCountryDao.read();
        countryList.sort(Comparator.comparingInt(Country::getId));
        return countryList;
    }

    public Optional<Country> getCountryById(int id) {
        return iCountryDao.read(id);
    }

    public Optional<Country> getCountryByName(String name) {
        return iCountryDao.getCountryByName(name);
    }

    public boolean addCountry(Country country) {
        return iCountryDao.create(country);
    }

    public boolean updateCountry(Country country) {
        return iCountryDao.update(country);
    }
}
