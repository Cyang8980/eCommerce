package com.Revature.daos;

import java.util.List;

public interface CrudDAO<T> {
    
    T save(T obj);

    T update(T obj);

    T delete(String ID);

    List<T> findAll();

    T findByID(String ID);
}
