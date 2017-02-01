package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.ClientDao;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.service.exceptions.DeleteGenericClientException;
import com.jcolaiacovo.armored.cars.service.exceptions.ModifyGenericClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class ClientService {

    private static final int GENERIC_CLIENT_ID = 1;

    private ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> getAllClients() {
        return this.clientDao.getAllClients();
    }

    public Client getClientById(int id) {
        return this.clientDao.getClientById(id);
    }

    public Client save(Client client) {
        if (this.isGenericClient(client.getId())) {
            throw new ModifyGenericClientException();
        } else {
            return this.clientDao.save(client);
        }
    }

    public void delete(int id) {
        if (this.isGenericClient(id)) {
            throw new DeleteGenericClientException();
        } else {
            this.clientDao.delete(id);
        }
    }

    private boolean isGenericClient(int idClient) {
        return idClient == GENERIC_CLIENT_ID;
    }

}
