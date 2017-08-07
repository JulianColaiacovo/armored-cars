package com.jcolaiacovo.armored.cars.domain.accountbalance;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountMovement {

    private String currencyCode;
    private BigDecimal debit;
    private BigDecimal credit;
    private Integer billId;
    private Integer collectionId;
    private DateTime dateTime;

    public AccountMovement(String currencyCode,
                           BigDecimal debit,
                           BigDecimal credit,
                           Integer billId,
                           Integer collectionId,
                           DateTime dateTime) {
        this.currencyCode = currencyCode;
        this.debit = debit;
        this.credit = credit;
        this.billId = billId;
        this.collectionId = collectionId;
        this.dateTime = dateTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
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

    public Integer getBillId() {
        return billId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }
}
