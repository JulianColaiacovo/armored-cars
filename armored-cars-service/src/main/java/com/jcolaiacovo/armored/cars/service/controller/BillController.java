package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.BillDTO;
import com.jcolaiacovo.armored.cars.api.model.UncollectedBillDTO;
import com.jcolaiacovo.armored.cars.domain.model.Bill;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import com.jcolaiacovo.armored.cars.domain.service.BillService;
import com.jcolaiacovo.armored.cars.domain.transformer.BillTransformer;
import com.jcolaiacovo.armored.cars.domain.transformer.UncollectedBillTransformer;
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
    private final UncollectedBillTransformer uncollectedBillTransformer;

    @Autowired
    public BillController(BillService billService,
                          BillTransformer billTransformer,
                          UncollectedBillTransformer uncollectedBillTransformer) {
        this.billService = billService;
        this.billTransformer = billTransformer;
        this.uncollectedBillTransformer = uncollectedBillTransformer;
    }

    @GetMapping
    public List<BillDTO> getAll() {
        List<Bill> bills = this.billService.getAll();
        return this.billTransformer.transformToDTOAll(bills);
    }

    @GetMapping(value = "search")
    public List<BillDTO> search(@RequestParam(required = false) BillTypeCode billTypeCode,
                                @RequestParam(required = false) String clientName) {
        List<Bill> bills = this.billService.search(billTypeCode, clientName);
        return this.billTransformer.transformToDTOAll(bills);
    }

    @GetMapping(value = "uncollected")
    public List<UncollectedBillDTO> getUncollectedBills() {
        List<Bill> bills = this.billService.getUncollectedBills();
        return this.uncollectedBillTransformer.transformToDTOAll(bills);
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

    @GetMapping("/next-number")
    public long getById(@RequestParam BillTypeCode billTypeCode) {
        return this.billService.getNextBillNumber(billTypeCode);
    }

}
