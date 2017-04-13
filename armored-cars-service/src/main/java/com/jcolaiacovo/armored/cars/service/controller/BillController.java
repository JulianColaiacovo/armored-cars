package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.BillDTO;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import com.jcolaiacovo.armored.cars.domain.transformer.BillTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;
    private final BillTransformer billTransformer;

    @Autowired
    public BillController(BillService billService, BillTransformer billTransformer) {
        this.billService = billService;
        this.billTransformer = billTransformer;
    }

    @GetMapping
    public List<BillDTO> getAll() {
        List<Bill> bills = this.billService.getAll();
        return this.billTransformer.transformToDTOAll(bills);
    }

    @GetMapping("/{id}")
    public BillDTO getById(@PathVariable int id) {
        Bill bill = this.billService.getById(id);
        return this.billTransformer.transformToDTO(bill);
    }

    @PostMapping
    public BillDTO save(@RequestBody BillDTO billDTO) {
        Bill bill = this.billTransformer.transform(billDTO);
        this.billService.save(bill);
        return this.billTransformer.transformToDTO(bill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.billService.delete(id);
    }

}
