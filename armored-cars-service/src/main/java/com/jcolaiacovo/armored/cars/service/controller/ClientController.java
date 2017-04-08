package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable int id) {
        return this.clientService.getById(id);
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        this.clientService.save(client);
        return client;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.clientService.delete(id);
    }

}
