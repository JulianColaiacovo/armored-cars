package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountMovementDTO {

    private BigDecimal debit;
    private BigDecimal credit;
    private DateTime dateTime;

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

}
