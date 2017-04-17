package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 10/04/2017.
 */
public class AdditionalDTO {

    private int id;
    private DateTime date;
    private int armoredId;
    private String currencyCode;
    private BigDecimal conversion;
    private BigDecimal amount;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getArmoredId() {
        return armoredId;
    }

    public void setArmoredId(int armoredId) {
        this.armoredId = armoredId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
