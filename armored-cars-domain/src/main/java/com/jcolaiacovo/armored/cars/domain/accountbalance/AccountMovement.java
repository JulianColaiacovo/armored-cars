package com.jcolaiacovo.armored.cars.domain.accountbalance;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountMovement {

    private BigDecimal debit;
    private BigDecimal credit;
    private DateTime dateTime;

    public AccountMovement(BigDecimal debit, BigDecimal credit, DateTime dateTime) {
        this.debit = debit;
        this.credit = credit;
        this.dateTime = dateTime;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
    
}
