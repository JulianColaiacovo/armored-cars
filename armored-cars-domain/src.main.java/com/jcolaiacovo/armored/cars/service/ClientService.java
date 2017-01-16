package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.ClientDao;
import com.jcolaiacovo.armored.cars.domain.model.Client;
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

}
