package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Additional;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class AdditionalDao extends AbstractDao<Additional> {

    @Autowired
    public AdditionalDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Additional.class, sessionFactory);
    }

    public List<Additional> getAll() {
        return (List<Additional>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from ADDITIONAL;")
                .addEntity(Additional.class)
                .list();
    }

}
