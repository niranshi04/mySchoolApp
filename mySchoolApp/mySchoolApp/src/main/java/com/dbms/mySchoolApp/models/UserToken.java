package com.dbms.mySchoolApp.models;

import java.util.UUID;

public class UserToken {
    private String emailAddress;
    private String token;

    public UserToken() {
    }

    public UserToken(String emailAddress) {
        this.emailAddress = emailAddress;
        this.token = UUID.randomUUID().toString();
    }

    public UserToken emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    /**
     * @return int return the userId
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param userId the userId to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return String return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}