package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.AdditionalCollection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class AdditionalCollectionDao extends AbstractDao<AdditionalCollection> {

    @Autowired
    public AdditionalCollectionDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(AdditionalCollection.class, sessionFactory);
    }

    public List<AdditionalCollection> getAll() {
        return (List<AdditionalCollection>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from ADDITIONAL_COLLECTION;")
                .addEntity(AdditionalCollection.class)
                .list();
    }

}
