package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Julian on 14/01/2017.
 */
@Repository
public class ClientDao extends AbstractDao<Client> {

    @Autowired
    public ClientDao(SessionFactory sessionFactory) {
        super(Client.class, sessionFactory);
    }

    public List<Client> getAll() {
        return (List<Client>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from CLIENT;")
                .addEntity(Client.class)
                .list();
    }

    public List<Client> search(String name, String document) {
        String likeName = Optional.ofNullable(name).map(s -> '%' + s + '%').orElse("%");
        String likeDocument = Optional.ofNullable(document).map(s -> '%' + s + '%').orElse("%");
        return (List<Client>) this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct cli from Client as cli where cli.name like :name and cli.document like :document")
                .setString("name", likeName)
                .setString("document", likeDocument)
                .list();
    }

}
