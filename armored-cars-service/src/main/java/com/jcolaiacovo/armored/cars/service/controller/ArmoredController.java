package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.ArmoredDTO;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.service.ArmoredService;
import com.jcolaiacovo.armored.cars.domain.transformer.ArmoredTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@RestController
@RequestMapping("/armoreds")
public class ArmoredController {

    private ArmoredService armoredService;
    private ArmoredTransformer armoredTransformer;

    @Autowired
    public ArmoredController(ArmoredService armoredService, ArmoredTransformer armoredTransformer) {
        this.armoredService = armoredService;
        this.armoredTransformer = armoredTransformer;
    }

    @GetMapping
    public List<ArmoredDTO> getAll() {
        List<Armored> armoreds = this.armoredService.getAll();
        return this.armoredTransformer.transformToDTOAll(armoreds);
    }

    @GetMapping("/{id}")
    public ArmoredDTO getById(@PathVariable int id) {
        Armored armored = this.armoredService.getById(id);
        return this.armoredTransformer.transformToDTO(armored);
    }

    @PostMapping
    public ArmoredDTO save(@RequestBody ArmoredDTO armoredDTO) {
        Armored armored = this.armoredTransformer.transform(armoredDTO);
        this.armoredService.save(armored);
        return this.armoredTransformer.transformToDTO(armored);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.armoredService.delete(id);
    }

}
