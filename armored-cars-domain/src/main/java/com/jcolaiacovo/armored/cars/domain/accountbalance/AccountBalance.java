package com.jcolaiacovo.armored.cars.domain.accountbalance;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountBalance {

    private List<AccountMovement> accountMovements;

    public AccountBalance(List<AccountMovement> accountMovements) {
        this.accountMovements = accountMovements;
    }

    public BigDecimal getBalance() {
        return this.accountMovements.stream()
                .map(accountMovement -> accountMovement.getCredit().subtract(accountMovement.getDebit()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<AccountMovement> getAccountMovements() {
        return accountMovements;
    }

}
