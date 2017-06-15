package com.jcolaiacovo.armored.cars.api.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountBalanceDTO {

    private List<AccountMovementDTO> accountMovements;
    private BigDecimal balance;

    public List<AccountMovementDTO> getAccountMovements() {
        return accountMovements;
    }

    public void setAccountMovements(List<AccountMovementDTO> accountMovements) {
        this.accountMovements = accountMovements;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
