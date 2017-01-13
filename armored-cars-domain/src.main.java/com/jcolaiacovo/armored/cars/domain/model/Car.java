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
@Table(name = "CAR")
public class Car {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "BRAND", length = 100)
    private String brand;

    @Column(name = "MODEL", length = 100)
    private String model;

    @Column(name = "CHASSIS_NUMBER", length = 100)
    private String chassisNumber;

    @Column(name = "MOTOR_NUMBER", length = 100)
    private String motorNumber;

    @Column(name = "DOMAIN", length = 100)
    private String domain;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMotorNumber() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber = motorNumber;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
