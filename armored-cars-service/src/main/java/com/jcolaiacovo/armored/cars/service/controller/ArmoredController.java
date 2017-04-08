package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.service.ArmoredService;
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

    @Autowired
    public ArmoredController(ArmoredService armoredService) {
        this.armoredService = armoredService;
    }

    @GetMapping
    public List<Armored> getAll() {
        return this.armoredService.getAll();
    }

    @GetMapping("/{id}")
    public Armored getById(@PathVariable int id) {
        return this.armoredService.getById(id);
    }

    @PostMapping
    public Armored save(@RequestBody Armored armored) {
        this.armoredService.save(armored);
        return armored;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.armoredService.delete(id);
    }

}
