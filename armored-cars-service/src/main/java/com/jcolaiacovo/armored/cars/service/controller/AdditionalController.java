package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.AdditionalDTO;
import com.jcolaiacovo.armored.cars.domain.model.Additional;
import com.jcolaiacovo.armored.cars.domain.service.AdditionalService;
import com.jcolaiacovo.armored.cars.domain.transformer.AdditionalTransformer;
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
    private AdditionalTransformer additionalTransformer;

    @Autowired
    public AdditionalController(AdditionalService additionalService, AdditionalTransformer additionalTransformer) {
        this.additionalService = additionalService;
        this.additionalTransformer = additionalTransformer;
    }

    @GetMapping
    public List<AdditionalDTO> getAll() {
        List<Additional> additionals = this.additionalService.getAll();
        return this.additionalTransformer.transformToDTOAll(additionals);
    }

    @GetMapping("/{id}")
    public AdditionalDTO getById(@PathVariable int id) {
        Additional additional = this.additionalService.getById(id);
        return this.additionalTransformer.transformToDTO(additional);
    }

    @PostMapping
    public AdditionalDTO save(@RequestBody AdditionalDTO additionalDTO) {
        Additional additional = this.additionalTransformer.transform(additionalDTO);
        this.additionalService.save(additional);
        return this.additionalTransformer.transformToDTO(additional);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.additionalService.delete(id);
    }

}
