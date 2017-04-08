package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.service.AliquotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/aliquots")
public class AliquotController {

    private AliquotService aliquotService;

    @Autowired
    public AliquotController(AliquotService aliquotService) {
        this.aliquotService = aliquotService;
    }

    @GetMapping
    public List<BigDecimal> getAllDocumentTypes() {
        return this.aliquotService.getAllAliquots();
    }

}
