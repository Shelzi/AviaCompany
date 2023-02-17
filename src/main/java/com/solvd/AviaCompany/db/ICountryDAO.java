package com.solvd.AviaCompany.db;

import java.util.List;

public interface ICountryDAO extends DAO{
    @Override
    boolean create(Object entity);

    @Override
    List read();

    @Override
    Object update(Object entity);

    @Override
    Object read(Number id);

    @Override
    boolean delete(Number id);

    @Override
    boolean delete(Object entity);
}
