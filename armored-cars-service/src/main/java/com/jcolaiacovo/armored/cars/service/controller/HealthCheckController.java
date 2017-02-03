package com.jcolaiacovo.armored.cars.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Julian on 01/02/2017.
 */
@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping
    public void healthCheck() {

    }

}
