package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import com.jcolaiacovo.armored.cars.domain.service.BillTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/bill-type-codes")
public class BillTypeCodeController {

    private BillTypeCodeService billTypeCodeService;

    @Autowired
    public BillTypeCodeController(BillTypeCodeService billTypeCodeService) {
        this.billTypeCodeService = billTypeCodeService;
    }

    @GetMapping
    public List<BillTypeCode> getBillTypeCodes(@RequestParam(required = false) Boolean enabled) {
        return this.billTypeCodeService.getBillTypeCodes(enabled);
    }

}
