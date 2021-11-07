package com.dbms.mySchoolApp.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
  
    @NotEmpty
    @Email
    private String emailAddress;
    
    private String password;
    private Date dateCreated;
    private boolean isEmailVerified;
    private Date lastLoginDate;
    private Time lastLoginTime;
    private String role;

    public User() {
        isEmailVerified = false;
    }

    public User(String password, String emailAddress, Date dateCreated ,boolean isEmailVerified,  Date lastLoginDate, Time lastLoginTime, String role) {
        this.password = password;
        this.emailAddress = emailAddress;
        this.dateCreated = dateCreated;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
        this.role = role;
        this.isEmailVerified = isEmailVerified;
    }

    /**
     * @return String return the token
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param token the token to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    /**
     * @return String return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return Date return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    /**
     * @return Date return the lastLoginDate
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * @return Time return the lastLoginTime
     */
    public Time getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime the lastLoginTime to set
     */
    public void setLastLoginTime(Time lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return String return the role
     */
    public String getRole() {
        return role;
    }
    
    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    /**
     * @return boolean return the isEmailVerified
     */
    public boolean isIsEmailVerified() {
        return isEmailVerified;
    }

    /**
     * @param isEmailVerified the isEmailVerified to set
     */
    public void setIsEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

}