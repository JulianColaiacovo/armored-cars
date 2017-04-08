package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Collection;
import com.jcolaiacovo.armored.cars.domain.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/collections")
public class CollectionController {

    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<Collection> getAll() {
        return this.collectionService.getAll();
    }

    @GetMapping("/{id}")
    public Collection getById(@PathVariable int id) {
        return this.collectionService.getById(id);
    }

    @PostMapping
    public Collection save(@RequestBody Collection collection) {
        this.collectionService.save(collection);
        return collection;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.collectionService.delete(id);
    }

}
