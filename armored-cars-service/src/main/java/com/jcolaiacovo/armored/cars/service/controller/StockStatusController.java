package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.StockStatus;
import com.jcolaiacovo.armored.cars.domain.service.StockStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 29/01/2017.
 */
@RestController
@RequestMapping("/stock-statuses")
public class StockStatusController {

    private StockStatusService stockStatusService;

    @Autowired
    public StockStatusController(StockStatusService stockStatusService) {
        this.stockStatusService = stockStatusService;
    }

    @GetMapping
    public List<StockStatus> getAll() {
        return this.stockStatusService.getAll();
    }

}
