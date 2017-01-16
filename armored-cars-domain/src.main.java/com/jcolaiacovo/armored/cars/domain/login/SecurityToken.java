package com.jcolaiacovo.armored.cars.domain.login;

public class SecurityToken {

    private String userName;
    private int token;

    public SecurityToken(int token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getToken() {
        return token;
    }

}
