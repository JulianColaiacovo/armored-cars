package com.jcolaiacovo.armored.cars.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "USER", length = 100, nullable = false)
    private String user;

    @Column(name = "PASSWORD", length = 66, nullable = false)
    private String password;

    @Column(name = "USER_LEVEL", length = 100, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserLevel userLevel;

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

}
