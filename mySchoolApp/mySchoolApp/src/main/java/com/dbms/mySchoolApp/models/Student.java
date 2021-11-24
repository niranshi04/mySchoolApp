package com.dbms.mySchoolApp.models;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Student {
    private int registrationNo;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfLeavingSchool;
    
    private String gender;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfAdmission;

    private String houseNumber;

    private String street;

    private String city;

    
    private String state;

    private User user;
    private String phoneNumber;

    public Student() {
        user = new User();
    }

    public Student(String name, int registrationNo, String gender, Date dateOfBirth, String houseNumber, String street, String city, String state, 
    		User user, Date dateOfAdmission, Date dateOfLeavingSchool, String phoneNumber) {
        this.name=name;
    	this.registrationNo = registrationNo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.dateOfAdmission = dateOfAdmission;
        this.dateOfLeavingSchool = dateOfLeavingSchool;
        this.user = user;
        this.phoneNumber=phoneNumber;
    }
    
    /**
     * @return String return the gender
     */
    public String getName() {
        return name;
    }

    /**
     * @param gender the gender to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int return the studentId
     */
    public int getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Date return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return String return the houseNumber
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * @param houseNumber the houseNumber to set
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * @return String return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Date return the dateOfBirth
     */
    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
    /**
     * @return Date return the dateOfBirth
     */
    public Date getDateOfLeavingSchool() {
        return dateOfLeavingSchool;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfLeavingSchool(Date dateOfLeavingSchool) {
        this.dateOfLeavingSchool = dateOfLeavingSchool;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * @return String return the state
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param state the state to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    

}