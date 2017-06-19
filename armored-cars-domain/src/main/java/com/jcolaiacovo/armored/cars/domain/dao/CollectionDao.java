package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class CollectionDao extends AbstractDao<Collection> {

    @Autowired
    public CollectionDao(SessionFactory sessionFactory) {
        super(Collection.class, sessionFactory);
    }

    public List<Collection> getByBillClientId(int clientId) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct collection from Collection as collection where collection.bill.billTo.id = :billToId")
                .setInteger("billToId", clientId)
                .list();
    }

    public List<Collection> getByArmoredId(int armoredId) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct collection from Collection as collection where collection.bill.armored.id = :armoredId")
                .setInteger("armoredId", armoredId)
                .list();
    }

    public BigDecimal getCollectedAmountByBillId(int billId) {
        List<BigDecimal> collections = this.getSessionFactory().getCurrentSession()
                .createQuery("select collection.totalAmount from Collection as collection where collection.bill.id = :billId")
                .setInteger("billId", billId)
                .list();
        return collections.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Collection> getAll() {
        return (List<Collection>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from COLLECTION;")
                .addEntity(Collection.class)
                .list();
    }

}
