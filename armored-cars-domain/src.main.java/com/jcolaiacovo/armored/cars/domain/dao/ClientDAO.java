package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Repository
public class ClientDao extends AbstractDao {

    @Autowired
    public ClientDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Client> getAllClients() {
        return (List<Client>) this.getSessionFactory().getCurrentSession().createQuery("select cli from Client as cli").list();
    }

    public Client getClientById(int id) {
        return (Client) this.getSessionFactory().getCurrentSession().get(Client.class, id);
    }

}
