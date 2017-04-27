package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.ClientDao;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.service.exceptions.DeleteGenericClientException;
import com.jcolaiacovo.armored.cars.domain.service.exceptions.ModifyGenericClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class ClientService extends AbstractDaoService<Client> {

    private static final int GENERIC_CLIENT_ID = 1;

    private ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> getAll() {
        return this.clientDao.getAll();
    }

    public Client getGenericClient() {
        return this.getById(GENERIC_CLIENT_ID);
    }

    public Optional<Client> findByName(String name) {
        return this.clientDao.findByName(name);
    }

    public List<Client> search(String name, String document) {
        return this.clientDao.search(name, document);
    }

    public void save(Client client) {
        if (this.isGenericClient(client)) {
            throw new ModifyGenericClientException();
        } else {
            super.save(client);
        }
    }

    public void delete(int id) {
        if (this.isGenericClient(id)) {
            throw new DeleteGenericClientException();
        } else {
            super.delete(id);
        }
    }

    @Override
    protected AbstractDao<Client> getDao() {
        return this.clientDao;
    }

    public boolean isGenericClient(Client client) {
        return this.isGenericClient(client.getId());
    }

    private boolean isGenericClient(int idClient) {
        return idClient == GENERIC_CLIENT_ID;
    }

}
