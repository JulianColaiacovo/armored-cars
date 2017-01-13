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
@Table(name = "ARMORED")
public class Armored {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "CODE", nullable = false)
    private int code;

    @Column(name = "ENTRY_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime entryDate;

    @Column(name = "DEPARTURE_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime departureDate;

    @Column(name = "DELIVERY_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILLING_AND_REFERENCE_ID", nullable = false)
    private BillingAndReference billingAndReference;

    @Column(name = "STOCK_STATUS", length = 50, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StockStatus stockStatus;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    public Armored() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(DateTime entryDate) {
        this.entryDate = entryDate;
    }

    public DateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(DateTime departureDate) {
        this.departureDate = departureDate;
    }

    public DateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(DateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BillingAndReference getBillingAndReference() {
        return billingAndReference;
    }

    public void setBillingAndReference(BillingAndReference billingAndReference) {
        this.billingAndReference = billingAndReference;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
