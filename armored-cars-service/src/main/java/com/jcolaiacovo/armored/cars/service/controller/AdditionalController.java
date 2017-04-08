package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Additional;
import com.jcolaiacovo.armored.cars.domain.service.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/additionals")
public class AdditionalController {

    private AdditionalService additionalService;

    @Autowired
    public AdditionalController(AdditionalService additionalService) {
        this.additionalService = additionalService;
    }

    @GetMapping
    public List<Additional> getAll() {
        return this.additionalService.getAll();
    }

    @GetMapping("/{id}")
    public Additional getById(@PathVariable int id) {
        return this.additionalService.getById(id);
    }

    @PostMapping
    public Additional save(@RequestBody Additional additional) {
        this.additionalService.save(additional);
        return additional;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.additionalService.delete(id);
    }

}
