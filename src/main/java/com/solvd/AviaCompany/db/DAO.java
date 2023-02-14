package com.solvd.AviaCompany.db;

import java.util.List;

public interface DAO<K extends Number, T> {
    public abstract boolean create(T entity);
    public abstract List<T> read();
    public abstract T update(T entity);
    public abstract T read(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
}