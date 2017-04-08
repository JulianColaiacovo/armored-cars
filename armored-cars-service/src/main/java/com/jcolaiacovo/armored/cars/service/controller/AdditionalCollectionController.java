package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.AdditionalCollection;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import com.jcolaiacovo.armored.cars.domain.service.AdditionalCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/additional-collections")
public class AdditionalCollectionController {

    private AdditionalCollectionService additionalCollectionService;

    @Autowired
    public AdditionalCollectionController(AdditionalCollectionService additionalCollectionService) {
        this.additionalCollectionService = additionalCollectionService;
    }

    @GetMapping
    public List<AdditionalCollection> getAll() {
        return this.additionalCollectionService.getAll();
    }

    @GetMapping("/{id}")
    public AdditionalCollection getById(@PathVariable int id) {
        return this.additionalCollectionService.getById(id);
    }

    @PostMapping
    public AdditionalCollection save(@RequestBody AdditionalCollection additionalCollection) {
        this.additionalCollectionService.save(additionalCollection);
        return additionalCollection;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.additionalCollectionService.delete(id);
    }

}
