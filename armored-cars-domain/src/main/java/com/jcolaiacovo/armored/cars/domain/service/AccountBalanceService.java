package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.accountbalance.AccountBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Julian on 15/06/2017.
 */
@Service
public class AccountBalanceService {

    private AccountMovementService accountMovementService;

    @Autowired
    public AccountBalanceService(AccountMovementService accountMovementService) {
        this.accountMovementService = accountMovementService;
    }

    public AccountBalance getAccountBalanceByClientId(int clientId) {
        return new AccountBalance(this.accountMovementService.getAccountMovementsByClientId(clientId));
    }

}
