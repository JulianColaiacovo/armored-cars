package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 10/04/2017.
 */
public class BillDTO {

    private int id;
    private DateTime date;
    private int armoredId;
    private BigDecimal conversion;
    private Long number;
    private String currencyCode;
    private BigDecimal aliquot;
    private BigDecimal taxedAmount;
    private BigDecimal untaxedAmount;
    private String billTypeCode;
    private BigDecimal vatAmount;
    private BigDecimal totalAmount;
    private Integer applyBillId;
    private String description;
    private int billToId;
    private Boolean financialAdvance;

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

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAliquot() {
        return aliquot;
    }

    public void setAliquot(BigDecimal aliquot) {
        this.aliquot = aliquot;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getUntaxedAmount() {
        return untaxedAmount;
    }

    public void setUntaxedAmount(BigDecimal untaxedAmount) {
        this.untaxedAmount = untaxedAmount;
    }

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
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

    public Integer getApplyBillId() {
        return applyBillId;
    }

    public void setApplyBillId(Integer applyBillId) {
        this.applyBillId = applyBillId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBillToId() {
        return billToId;
    }

    public void setBillToId(int billToId) {
        this.billToId = billToId;
    }

    public Boolean getFinancialAdvance() {
        return financialAdvance;
    }

    public void setFinancialAdvance(Boolean financialAdvance) {
        this.financialAdvance = financialAdvance;
    }

}
