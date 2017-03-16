package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Currency;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 09/01/2017.
 */
@Repository
public class CurrencyDao extends AbstractDao<Currency> {

    @Autowired
    public CurrencyDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Currency.class, sessionFactory);
    }

    public List<Currency> getAllCurrencies() {
        return this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CURRENCY;")
                .addEntity(Currency.class)
                .list();
    }

}
