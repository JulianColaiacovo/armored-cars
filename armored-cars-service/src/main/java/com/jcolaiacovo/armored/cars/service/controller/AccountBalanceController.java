package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.api.model.AccountBalanceDTO;
import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountBalance;
import com.jcolaiacovo.armored.cars.domain.service.AccountBalanceService;
import com.jcolaiacovo.armored.cars.domain.transformer.AccountBalanceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Julian on 15/06/2017.
 */
@RestController
@RequestMapping("account-balances")
public class AccountBalanceController {

    private AccountBalanceService accountBalanceService;
    private AccountBalanceTransformer accountBalanceTransformer;

    @Autowired
    public AccountBalanceController(AccountBalanceService accountBalanceService,
                                    AccountBalanceTransformer accountBalanceTransformer) {
        this.accountBalanceService = accountBalanceService;
        this.accountBalanceTransformer = accountBalanceTransformer;
    }

    @GetMapping("client/{clientId}")
    public AccountBalanceDTO getAccountBalanceByClientId(@PathVariable int clientId) {
        AccountBalance accountBalance = this.accountBalanceService.getAccountBalanceByClientId(clientId);
        return this.accountBalanceTransformer.transformToDTO(accountBalance);
    }

    @GetMapping("armored/{armoredId}")
    public AccountBalanceDTO getAccountBalanceByArmoredId(@PathVariable int armoredId) {
        AccountBalance accountBalance = this.accountBalanceService.getAccountBalanceByArmoredId(armoredId);
        return this.accountBalanceTransformer.transformToDTO(accountBalance);
    }

}
