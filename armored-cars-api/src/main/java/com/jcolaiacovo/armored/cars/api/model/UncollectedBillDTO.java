package com.jcolaiacovo.armored.cars.api.model;

import java.math.BigDecimal;

/**
 * Created by Julian on 18/06/2017.
 */
public class UncollectedBillDTO {

    private BillDTO bill;
    private BigDecimal totalAmount;
    private BigDecimal creditNoteAmount;
    private BigDecimal collectedAmount;
    private BigDecimal amountToCollect;

    public BillDTO getBill() {
        return bill;
    }

    public void setBill(BillDTO bill) {
        this.bill = bill;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(BigDecimal collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public BigDecimal getCreditNoteAmount() {
        return creditNoteAmount;
    }

    public void setCreditNoteAmount(BigDecimal creditNoteAmount) {
        this.creditNoteAmount = creditNoteAmount;
    }

    public BigDecimal getAmountToCollect() {
        return amountToCollect;
    }

    public void setAmountToCollect(BigDecimal amountToCollect) {
        this.amountToCollect = amountToCollect;
    }
}
