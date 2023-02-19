package com.solvd.aviacompany.db.dao;

import java.util.List;
import java.util.Optional;

public interface IBaseDAO<T> {
    boolean create(T entity);
    List<T> read();
    boolean update(T entity);
    Optional<T> read(int id);
    boolean delete(int id);
    boolean delete(T entity);
}