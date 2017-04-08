package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.CommissionPayment;
import com.jcolaiacovo.armored.cars.domain.service.CommissionPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/commission-payments")
public class CommissionPaymentController {

    private CommissionPaymentService commissionPaymentService;

    @Autowired
    public CommissionPaymentController(CommissionPaymentService commissionPaymentService) {
        this.commissionPaymentService = commissionPaymentService;
    }

    @GetMapping
    public List<CommissionPayment> getAll() {
        return this.commissionPaymentService.getAll();
    }

    @GetMapping("/{id}")
    public CommissionPayment getById(@PathVariable int id) {
        return this.commissionPaymentService.getById(id);
    }

    @PostMapping
    public CommissionPayment save(@RequestBody CommissionPayment commissionPayment) {
        this.commissionPaymentService.save(commissionPayment);
        return commissionPayment;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.commissionPaymentService.delete(id);
    }

}
