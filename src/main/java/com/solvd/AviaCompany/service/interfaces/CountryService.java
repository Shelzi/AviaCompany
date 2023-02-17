package com.solvd.AviaCompany.service.interfaces;

import com.solvd.AviaCompany.hierarchy.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAllCountries();
    Optional<Country> getCountryById(int id);
    Optional<Country> getCountryByName(String name);
    boolean addCountry(Country country);
    Optional<Country> updateCountry(Country country);

}
