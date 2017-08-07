package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 11/04/2017.
 */
public class CollectionDTO {

    private int id;
    private Integer billId;
    private String currencyCode;
    private BigDecimal baseAmount;
    private BigDecimal iibbAmount;
    private BigDecimal sussAmount;
    private BigDecimal gainAmount;
    private BigDecimal vatAmount;
    private BigDecimal totalAmount;
    private DateTime date;
    private BigDecimal description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getIibbAmount() {
        return iibbAmount;
    }

    public void setIibbAmount(BigDecimal iibbAmount) {
        this.iibbAmount = iibbAmount;
    }

    public BigDecimal getSussAmount() {
        return sussAmount;
    }

    public void setSussAmount(BigDecimal sussAmount) {
        this.sussAmount = sussAmount;
    }

    public BigDecimal getGainAmount() {
        return gainAmount;
    }

    public void setGainAmount(BigDecimal gainAmount) {
        this.gainAmount = gainAmount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public BigDecimal getDescription() {
        return description;
    }

    public void setDescription(BigDecimal description) {
        this.description = description;
    }
}
