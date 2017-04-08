package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.CommissionPayment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class CommissionPaymentDao extends AbstractDao<CommissionPayment> {

    @Autowired
    public CommissionPaymentDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(CommissionPayment.class, sessionFactory);
    }

    public List<CommissionPayment> getAll() {
        return (List<CommissionPayment>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from COMMISSION_PAYMENT;")
                .addEntity(CommissionPayment.class)
                .list();
    }

}
