package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.BillingAndReference;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Repository
public class BillingAndReferenceDao extends AbstractDao<BillingAndReference> {

    @Autowired
    public BillingAndReferenceDao(SessionFactory sessionFactory) {
        super(BillingAndReference.class, sessionFactory);
    }

    public List<BillingAndReference> getAll() {
        return (List<BillingAndReference>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILLING_AND_REFERENCE;")
                .addEntity(BillingAndReference.class)
                .list();
    }

}
