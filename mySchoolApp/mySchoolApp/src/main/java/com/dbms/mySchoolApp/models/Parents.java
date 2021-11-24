package com.dbms.mySchoolApp.models;

import javax.validation.constraints.NotEmpty;

public class Parents {
    @NotEmpty
    private String motherName;
    private int registrationNo;
    @NotEmpty
    private String motherJob;

    @NotEmpty
    private String fatherName;

    @NotEmpty
    private String fatherJob;
    
    private String phoneNumber1;
    private String phoneNumber2;
    
    public Parents() {

    }


    public Parents(int registrationNo, String motherName, String motherJob, String fatherName, String fatherJob,
    		String phoneNumber1, String phoneNumber2) {
        this.registrationNo = registrationNo;
        this.motherName = motherName;
        this.motherJob = motherJob;
        this.fatherName = fatherName;
        this.fatherJob = fatherJob;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    /**
     * @return String return the name
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
     * @return String return the address
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * @param address the address to set
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     * @return String return the email
     */
    public String getMotherJob() {
        return motherJob;
    }

    /**
     * @param email the email to set
     */
    public void setMotherJob(String motherJob) {
        this.motherJob = motherJob;
    }

    /**
     * @return String return the relationWithStudent
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param relationWithStudent the relationWithStudent to set
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    /**
     * @return String return the email
     */
    public String getFatherJob() {
        return fatherJob;
    }

    /**
     * @param email the email to set
     */
    public void setFatherJob(String fatherJob) {
        this.fatherJob = fatherJob;
    }

    /**
     * @return String return the state
     */
    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    /**
     * @param state the state to set
     */
    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }
    /**
     * @return String return the state
     */
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    /**
     * @param state the state to set
     */
    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}
