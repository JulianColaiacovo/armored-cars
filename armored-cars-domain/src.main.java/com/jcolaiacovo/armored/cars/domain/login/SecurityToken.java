package com.jcolaiacovo.armored.cars.domain.login;

public class SecurityToken {
    private String userEmail;
    private String token;

    public SecurityToken() {
    }

    public SecurityToken(String token, String userEmail) {
        this.token = token;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
