package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.service.ArmoredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 14/01/2017.
 */
@RestController
@RequestMapping("/armored")
public class ArmoredController {

    private ArmoredService armoredService;

    @Autowired
    public ArmoredController(ArmoredService armoredService) {
        this.armoredService = armoredService;
    }

    @GetMapping
    public List<Armored> getAllArmoreds() {
        return this.armoredService.getAllArmoreds();
    }

    @GetMapping("/{id}")
    public Armored getArmoredById(@PathVariable int id) {
        return this.armoredService.getById(id);
    }

    @PostMapping("/save")
    public Armored saveArmored(@RequestBody Armored armored) {
        this.armoredService.save(armored);
        return armored;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArmored(@PathVariable int id) {
        this.armoredService.delete(id);
    }

}
