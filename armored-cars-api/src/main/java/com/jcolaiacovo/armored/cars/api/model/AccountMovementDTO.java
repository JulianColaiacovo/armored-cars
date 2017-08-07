package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 15/06/2017.
 */
public class AccountMovementDTO {

    private String currencyCode;
    private BigDecimal debit;
    private BigDecimal credit;
    private Integer billId;
    private Integer collectionId;
    private DateTime dateTime;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
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

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

}
