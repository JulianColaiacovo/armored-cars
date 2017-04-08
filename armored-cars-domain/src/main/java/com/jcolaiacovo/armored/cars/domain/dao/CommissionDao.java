package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Commission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class CommissionDao extends AbstractDao<Commission> {

    @Autowired
    public CommissionDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Commission.class, sessionFactory);
    }

    public List<Commission> getAll() {
        return (List<Commission>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from COMMISSION;")
                .addEntity(Commission.class)
                .list();
    }

}
