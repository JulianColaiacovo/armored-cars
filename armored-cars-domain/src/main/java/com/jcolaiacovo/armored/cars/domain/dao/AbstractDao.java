package com.jcolaiacovo.armored.cars.domain.dao;

import org.hibernate.SessionFactory;

/**
 * Created by Julian on 09/01/2017.
 */
public abstract class AbstractDao {

    private SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
