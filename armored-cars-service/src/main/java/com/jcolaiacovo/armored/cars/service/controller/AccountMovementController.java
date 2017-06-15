package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.AccountMovementDTO;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountMovement;
import com.jcolaiacovo.armored.cars.domain.service.AccountMovementService;
import com.jcolaiacovo.armored.cars.domain.transformer.AccountMovementTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 15/06/2017.
 */
@RestController
@RequestMapping("/account-movements")
public class AccountMovementController {

    private AccountMovementService accountMovementService;
    private AccountMovementTransformer accountMovementTransformer;

    @Autowired
    public AccountMovementController(AccountMovementService accountMovementService,
                                     AccountMovementTransformer accountMovementTransformer) {
        this.accountMovementService = accountMovementService;
        this.accountMovementTransformer = accountMovementTransformer;
    }

    @GetMapping
    public List<AccountMovementDTO> getAccountMovementsByClientId(@RequestParam(required = true) int clientId) {
        List<AccountMovement> accountMovements = this.accountMovementService.getAccountMovementsByClientId(clientId);
        return this.accountMovementTransformer.transformToDTOAll(accountMovements);
    }

}
