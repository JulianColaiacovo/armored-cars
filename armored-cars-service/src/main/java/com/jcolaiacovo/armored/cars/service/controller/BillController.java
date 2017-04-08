package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/bills")
public class BillController {

    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAll() {
        return this.billService.getAll();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable int id) {
        return this.billService.getById(id);
    }

    @PostMapping
    public Bill save(@RequestBody Bill bill) {
        this.billService.save(bill);
        return bill;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.billService.delete(id);
    }

}
