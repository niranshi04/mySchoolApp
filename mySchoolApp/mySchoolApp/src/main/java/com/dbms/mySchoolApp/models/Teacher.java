package com.dbms.mySchoolApp.models;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Teacher {
    private int teacherId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfJoining;
    
    private String gender;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    private String houseNumber;

    private String street;

    private String city;

    
    private String state;

    private User user;
    private String phoneNumber;
    private int subject1;
    private int subject2;
    private int subject3;
    private String classType;
    

    public Teacher() {
        user = new User();
    }

    public Teacher(String name, int teacherId, String gender, Date dateOfBirth, String houseNumber, String street, String city, String state, 
    		User user, Date dateOfJoining, String phoneNumber,
    		int subject1,int subject2, int subject3,  String classType) {
        this.name=name;
    	this.teacherId = teacherId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.dateOfJoining = dateOfJoining;
        this.subject1= subject1;
        this.subject2= subject2;
        this.subject3= subject3;
        this.classType= classType;
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
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
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

    /**
     * @return String return the state
     */
    public String getClassType() {
        return classType;
    }

    /**
     * @param state the state to set
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * @return String return the state
     */
    public int getSubject1() {
        return subject1;
    }

    /**
     * @param state the state to set
     */
    public void setSubject1(int subject1) {
        this.subject1 = subject1;
    }
    /**
     * @return String return the state
     */
    public int getSubject2() {
        return subject2;
    }

    /**
     * @param state the state to set
     */
    public void setSubject2(int subject2) {
        this.subject2 = subject2;
    } /**
     * @return String return the state
     */
    public int getSubject3() {
        return subject3;
    }

    /**
     * @param state the state to set
     */
    public void setSubject3(int subject3) {
        this.subject3 = subject3;
    }
    

}