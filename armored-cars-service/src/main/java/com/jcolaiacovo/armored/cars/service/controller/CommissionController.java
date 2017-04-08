package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Commission;
import com.jcolaiacovo.armored.cars.domain.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/commissions")
public class CommissionController {

    private CommissionService commissionService;

    @Autowired
    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping
    public List<Commission> getAll() {
        return this.commissionService.getAll();
    }

    @GetMapping("/{id}")
    public Commission getById(@PathVariable int id) {
        return this.commissionService.getById(id);
    }

    @PostMapping
    public Commission save(@RequestBody Commission commission) {
        this.commissionService.save(commission);
        return commission;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.commissionService.delete(id);
    }

}
