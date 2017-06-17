package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Julian on 16/03/2017.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "COLLECTION")
public class Collection {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BILL_ID")
    private Bill bill;

    @Column(name = "BASE_AMOUNT", nullable = false)
    private BigDecimal baseAmount;

    @Column(name = "IIBB_AMOUNT", nullable = false)
    private BigDecimal iibbAmount;

    @Column(name = "SUSS_AMOUNT", nullable = false)
    private BigDecimal sussAmount;

    @Column(name = "GAIN_AMOUNT", nullable = false)
    private BigDecimal gainAmount;

    @Column(name = "VAT_AMOUNT", nullable = false)
    private BigDecimal vatAmount;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @Column(name = "DESCRIPTION")
    private BigDecimal description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
