package com.solvd.AviaCompany.db.MySql;

import com.solvd.AviaCompany.db.ICountryDAO;

import java.util.List;

public class CountryDAO implements ICountryDAO {


    @Override
    public boolean create(Object entity) {
        return false;
    }

    @Override
    public List read() {
        return null;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public Object read(Number id) {
        return null;
    }

    @Override
    public boolean delete(Number id) {
        return false;
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }
}
