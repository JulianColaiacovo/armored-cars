package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Armored;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 21/02/2017.
 */
@Repository
public class ArmoredDao extends AbstractDao<Armored> {

    @Autowired
    public ArmoredDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Armored.class, sessionFactory);
    }

    public List<Armored> getAllArmoreds() {
        return (List<Armored>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from ARMORED;")
                .addEntity(Armored.class)
                .list();
    }

}
