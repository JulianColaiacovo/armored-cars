package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by gabrieldyck on 22/11/16.
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "USER")
public class User {

    @GeneratedValue
    @Id
    @Column(name="ID")
    private long id;

    @Column(name="USER")
    private String user;

    @Column(name="PASS")
    private String pass;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public User() {
    }
}
