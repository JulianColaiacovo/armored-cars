package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return this.clientService.getById(id);
    }

    @PostMapping("/save")
    public Client saveClient(@RequestBody Client client) {
        this.clientService.save(client);
        return client;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable int id) {
        this.clientService.delete(id);
    }

}
