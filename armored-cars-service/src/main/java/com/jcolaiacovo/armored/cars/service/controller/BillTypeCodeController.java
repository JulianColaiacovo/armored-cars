package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.BillTypeCodeDTO;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import com.jcolaiacovo.armored.cars.domain.service.BillTypeCodeService;
import com.jcolaiacovo.armored.cars.domain.transformer.BillTypeCodeTransformer;
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
@RequestMapping("bill-type-codes")
public class BillTypeCodeController {

    private BillTypeCodeTransformer billTypeCodeTransformer;
    private BillTypeCodeService billTypeCodeService;

    @Autowired
    public BillTypeCodeController(BillTypeCodeTransformer billTypeCodeTransformer,
                                  BillTypeCodeService billTypeCodeService) {
        this.billTypeCodeTransformer = billTypeCodeTransformer;
        this.billTypeCodeService = billTypeCodeService;
    }

    @GetMapping
    public List<BillTypeCodeDTO> getAll(@RequestParam(required = false) Boolean enabled) {
        return this.billTypeCodeTransformer.transformToDTOAll(this.billTypeCodeService.getBillTypeCodes(enabled));
    }

    @GetMapping(value = "possible-applies")
    public List<BillTypeCodeDTO> getPossibleBillTypeToApply(@RequestParam BillTypeCode billTypeCode) {
        return this.billTypeCodeTransformer.transformToDTOAll(this.billTypeCodeService.getPossibleBillTypeToApply(billTypeCode));
    }

}
