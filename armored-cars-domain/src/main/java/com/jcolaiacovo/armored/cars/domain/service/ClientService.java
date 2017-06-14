package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.ClientDao;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.parser.client.ClientExcelParser;
import com.jcolaiacovo.armored.cars.domain.service.exceptions.DeleteGenericClientException;
import com.jcolaiacovo.armored.cars.domain.service.exceptions.ModifyGenericClientException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Julian on 14/01/2017.
 */
@Transactional
@Service
public class ClientService extends AbstractDaoService<Client> {

    private static final int GENERIC_CLIENT_ID = 1;

    private ClientDao clientDao;
    private ClientExcelParser clientExcelParser;

    @Autowired
    public ClientService(ClientDao clientDao,
                         ClientExcelParser clientExcelParser) {
        this.clientDao = clientDao;
        this.clientExcelParser = clientExcelParser;
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

    public void loadExcel(MultipartFile excel) {
        try {
            Workbook workbook = new HSSFWorkbook(excel.getInputStream());
            List<Client> clients = this.clientExcelParser.parse(workbook);
            List<Client> bindClients = this.bind(clients);
            this.clientDao.saveAll(bindClients);
        } catch (IOException e) {
            throw new RuntimeException("Invalid excel", e);
        }
    }
    @Override
    protected AbstractDao<Client> getDao() {
        return this.clientDao;
    }

    public boolean isGenericClient(Client client) {
        return this.isGenericClient(client.getId());
    }


    public List<Client> bind(List<Client> clients) {
        return clients.parallelStream().map(this::bind).collect(Collectors.toList());
    }

    private Client bind(Client client) {
        Optional<Client> clientOptional = this.findByName(client.getName());
        if (clientOptional.isPresent()) {
            Client oldClient = clientOptional.get();
            client.setId(oldClient.getId());
        }
        return this.clientDao.merge(client);
    }

    private boolean isGenericClient(int idClient) {
        return idClient == GENERIC_CLIENT_ID;
    }

}
