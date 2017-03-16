package com.jcolaiacovo.armored.cars.domain.dao;

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
public class BillDao extends AbstractDao<Bill> {

    @Autowired
    public BillDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Bill.class, sessionFactory);
    }

    public List<Bill> getAllBills() {
        return (List<Bill>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL;")
                .addEntity(Bill.class)
                .list();
    }

}
