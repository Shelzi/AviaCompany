package com.solvd.aviacompany.db.dao;

import java.util.List;

public interface IBaseDAO<K extends Number, T> {
    public abstract boolean create(T entity);
    public abstract List<T> read();
    public abstract T update(T entity);
    public abstract T read(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
}