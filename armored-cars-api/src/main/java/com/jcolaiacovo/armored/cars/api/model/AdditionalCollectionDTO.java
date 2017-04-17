package com.jcolaiacovo.armored.cars.api.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by Julian on 16/04/2017.
 */
public class AdditionalCollectionDTO {

    private int id;
    private DateTime date;
    private int additionalId;
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

    public int getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(int additionalId) {
        this.additionalId = additionalId;
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
