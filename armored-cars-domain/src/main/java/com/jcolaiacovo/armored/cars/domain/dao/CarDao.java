package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Repository
public class CarDao extends AbstractDao<Car> {

    @Autowired
    public CarDao(SessionFactory sessionFactory) {
        super(Car.class, sessionFactory);
    }

    public List<Car> getAll() {
        return (List<Car>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CAR;")
                .addEntity(Car.class)
                .list();
    }

}
