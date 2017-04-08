package com.jcolaiacovo.armored.cars.domain.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Julian on 09/01/2017.
 */
public abstract class AbstractDao<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public T getById(int id) {
        return (T) this.getSessionFactory().getCurrentSession().get(this.clazz, id);
    }

    @Transactional
    public void save(T object) {
        this.getSessionFactory().getCurrentSession().saveOrUpdate(object);
    }

    @Transactional
    public void delete(int id) {
        Object object = this.getSessionFactory().getCurrentSession().load(this.clazz, id);
        this.getSessionFactory().getCurrentSession().delete(object);
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

}
