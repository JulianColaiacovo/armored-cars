package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;

/**
 * Created by Julian on 15/03/2017.
 */
public abstract class AbstractDaoService<T> {

    public T getById(int id) {
        return this.getDao().getById(id);
    }

    public void save(T object) {
        this.getDao().save(object);
    }

    public void delete(int id) {
        this.getDao().delete(id);
    }

    protected abstract AbstractDao<T> getDao();

}
