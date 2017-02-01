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
@Table(name = "CURRENCY")
public class Currency {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Currency() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
