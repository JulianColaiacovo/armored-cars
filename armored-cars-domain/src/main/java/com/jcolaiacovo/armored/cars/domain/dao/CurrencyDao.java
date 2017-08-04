package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Currency;
import org.hibernate.Query;
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

    public Currency getByCode(String code) {
        Query query = this.getSessionFactory().getCurrentSession().createQuery("select distinct currency from Currency as currency where currency.code = :code")
                .setString("code", code);
        return (Currency) query.uniqueResult();
    }

    public List<Currency> getAll() {
        return this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CURRENCY;")
                .addEntity(Currency.class)
                .list();
    }

    public List<Currency> getEnabledCurrencies() {
        return this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CURRENCY where CODE in ('ARS');")
                .addEntity(Currency.class)
                .list();
    }

    public List<Currency> getDisablesCurrencies() {
        return this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CURRENCY where CODE not in ('ARS');")
                .addEntity(Currency.class)
                .list();
    }

}
