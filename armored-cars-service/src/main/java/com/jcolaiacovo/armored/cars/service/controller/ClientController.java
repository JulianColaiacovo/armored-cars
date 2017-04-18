package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.ClientDTO;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.service.ClientService;
import com.jcolaiacovo.armored.cars.domain.transformer.ClientTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientTransformer clientTransformer;

    @Autowired
    public ClientController(ClientService clientService, ClientTransformer clientTransformer) {
        this.clientService = clientService;
        this.clientTransformer = clientTransformer;
    }

    @GetMapping
    public List<ClientDTO> getAll() {
        List<Client> clients = this.clientService.getAll();
        return this.clientTransformer.transformToDTOAll(clients);
    }

    @GetMapping("/search")
    public List<ClientDTO> search(@RequestParam(required = false) String name, @RequestParam(required = false) String document) {
        List<Client> clients = this.clientService.search(name, document);
        return this.clientTransformer.transformToDTOAll(clients);
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable int id) {
        Client client = this.clientService.getById(id);
        return this.clientTransformer.transformToDTO(client);
    }

    @PostMapping
    public ClientDTO save(@RequestBody ClientDTO clientDTO) {
        Client client = this.clientTransformer.transform(clientDTO);
        this.clientService.save(client);
        return this.clientTransformer.transformToDTO(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.clientService.delete(id);
    }

}
