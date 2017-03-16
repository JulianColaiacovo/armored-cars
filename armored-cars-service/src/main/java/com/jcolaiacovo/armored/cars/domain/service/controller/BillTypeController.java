package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.BillType;
import com.jcolaiacovo.armored.cars.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@RestController
@RequestMapping("/bill-type")
public class BillTypeController {

    private BillTypeService billTypeService;

    @Autowired
    public BillTypeController(BillTypeService billTypeService) {
        this.billTypeService = billTypeService;
    }

    @GetMapping
    public List<BillType> getAllDocumentTypes() {
        return this.billTypeService.getAllBillTypes();
    }

}
