package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class BillDao extends AbstractDao<Bill> {

    @Autowired
    public BillDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Bill.class, sessionFactory);
    }

    public Optional<Bill> getLastBill(BillTypeCode billTypeCode) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct bill from Bill as bill where bill.billTypeCode = :billTypeCode order by bill.number desc")
                .setString("billTypeCode", billTypeCode.name())
                .list()
                .stream()
                .map(Bill.class::cast)
                .findFirst();
    }

    public List<Bill> getAll() {
        return (List<Bill>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from BILL;")
                .addEntity(Bill.class)
                .list();
    }

}
