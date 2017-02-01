package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Julian on 09/01/2017.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "ADDITIONAL")
public class Additional {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARMORED_ID", nullable = false)
    private Armored armored;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "CONVERSION", nullable = false)
    private BigDecimal conversion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID", nullable = false)
    private Currency currency;

    @Column(name = "DESCRIPTION")
    private String description;

    public Additional() {
    }

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

    public Armored getArmored() {
        return armored;
    }

    public void setArmored(Armored armored) {
        this.armored = armored;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
