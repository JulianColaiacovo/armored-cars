package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 03/02/2017.
 */
@Repository
public class CollectionDao extends AbstractDao<Collection> {

    @Autowired
    public CollectionDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Collection.class, sessionFactory);
    }

    public List<Collection> getAllCollections() {
        return (List<Collection>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from COLLECTION;")
                .addEntity(Collection.class)
                .list();
    }

}
