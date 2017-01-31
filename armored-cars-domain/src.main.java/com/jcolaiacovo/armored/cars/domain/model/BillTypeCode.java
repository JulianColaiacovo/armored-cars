package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by Julian on 09/01/2017.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "BILL_TYPE_CODE")
public class BillTypeCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "BILL_TYPE", length = 100, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BillType billType;

    @Column(name = "CODE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BillCode billCode;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    public BillTypeCode() {
    }

    public int getId() {
        return id;
    }

    public BillType getBillType() {
        return billType;
    }

    public BillCode getBillCode() {
        return billCode;
    }

    public boolean getEnabled() {
        return enabled;
    }

}
