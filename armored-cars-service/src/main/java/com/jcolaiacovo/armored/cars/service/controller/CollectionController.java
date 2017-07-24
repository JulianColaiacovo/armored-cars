package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.CollectionDTO;
import com.jcolaiacovo.armored.cars.domain.model.Collection;
import com.jcolaiacovo.armored.cars.domain.service.CollectionService;
import com.jcolaiacovo.armored.cars.domain.transformer.CollectionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionTransformer collectionTransformer;

    @Autowired
    public CollectionController(CollectionService collectionService,
                                CollectionTransformer collectionTransformer) {
        this.collectionService = collectionService;
        this.collectionTransformer = collectionTransformer;
    }

    @GetMapping
    public List<CollectionDTO> getAll() {
        List<Collection> collections = this.collectionService.getAll();
        return this.collectionTransformer.transformToDTOAll(collections);
    }

    @GetMapping("/{id}")
    public CollectionDTO getById(@PathVariable int id) {
        Collection collection = this.collectionService.getById(id);
        return this.collectionTransformer.transformToDTO(collection);
    }

    @PostMapping
    public CollectionDTO save(@RequestBody CollectionDTO collectionDTO) {
        Collection collection = this.collectionTransformer.transform(collectionDTO);
        this.collectionService.save(collection);
        return this.collectionTransformer.transformToDTO(collection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.collectionService.delete(id);
    }

}
