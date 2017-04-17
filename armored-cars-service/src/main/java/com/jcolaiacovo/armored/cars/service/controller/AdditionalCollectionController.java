package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.AdditionalCollectionDTO;
import com.jcolaiacovo.armored.cars.domain.model.AdditionalCollection;
import com.jcolaiacovo.armored.cars.domain.service.AdditionalCollectionService;
import com.jcolaiacovo.armored.cars.domain.transformer.AdditionalCollectionTransformer;
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
    private AdditionalCollectionTransformer additionalCollectionTransformer;

    @Autowired
    public AdditionalCollectionController(AdditionalCollectionService additionalCollectionService,
                                          AdditionalCollectionTransformer additionalCollectionTransformer) {
        this.additionalCollectionService = additionalCollectionService;
        this.additionalCollectionTransformer = additionalCollectionTransformer;
    }

    @GetMapping
    public List<AdditionalCollectionDTO> getAll() {
        List<AdditionalCollection> additionalCollections = this.additionalCollectionService.getAll();
        return this.additionalCollectionTransformer.transformToDTOAll(additionalCollections);
    }

    @GetMapping("/{id}")
    public AdditionalCollectionDTO getById(@PathVariable int id) {
        AdditionalCollection additionalCollection = this.additionalCollectionService.getById(id);
        return this.additionalCollectionTransformer.transformToDTO(additionalCollection);
    }

    @PostMapping
    public AdditionalCollectionDTO save(@RequestBody AdditionalCollectionDTO additionalCollectionDTO) {
        AdditionalCollection additionalCollection = this.additionalCollectionTransformer.transform(additionalCollectionDTO);
        this.additionalCollectionService.save(additionalCollection);
        return this.additionalCollectionTransformer.transformToDTO(additionalCollection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.additionalCollectionService.delete(id);
    }

}
