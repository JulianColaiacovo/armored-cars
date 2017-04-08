package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.BillingAndReference;
import com.jcolaiacovo.armored.cars.domain.service.BillingAndReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/billing-and-references")
public class BillingAndReferenceController {

    private BillingAndReferenceService billingAndReferenceService;

    @Autowired
    public BillingAndReferenceController(BillingAndReferenceService billingAndReferenceService) {
        this.billingAndReferenceService = billingAndReferenceService;
    }

    @GetMapping
    public List<BillingAndReference> getAll() {
        return this.billingAndReferenceService.getAll();
    }

    @GetMapping("/{id}")
    public BillingAndReference getById(@PathVariable int id) {
        return this.billingAndReferenceService.getById(id);
    }

    @PostMapping
    public BillingAndReference save(@RequestBody BillingAndReference billingAndReference) {
        this.billingAndReferenceService.save(billingAndReference);
        return billingAndReference;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.billingAndReferenceService.delete(id);
    }

}
