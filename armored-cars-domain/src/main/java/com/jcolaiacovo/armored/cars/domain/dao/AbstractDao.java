package com.jcolaiacovo.armored.cars.domain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void saveAll(List<T> objects) {
        Session currentSession = this.getSessionFactory().getCurrentSession();
        objects.parallelStream().forEach(currentSession::saveOrUpdate);
    }

    @Transactional
    public void delete(int id) {
        Object object = this.getSessionFactory().getCurrentSession().load(this.clazz, id);
        this.getSessionFactory().getCurrentSession().delete(object);
    }

    @Transactional
    public T merge(T object) {
        return (T) this.getSessionFactory().getCurrentSession().merge(object);
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

}
