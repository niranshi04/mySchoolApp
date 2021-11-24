package com.dbms.mySchoolApp.models;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class StudentFees {
    private int registrationNo;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date scholarshipExpiry;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastDate;
    
    private int scholarshipPercent;



    public StudentFees() {
    }

    public StudentFees(int registrationNo, Date scholarshipExpiry, Date lastDate, int scholarshipPercent) {
        this.registrationNo=registrationNo;
    	this.scholarshipExpiry = scholarshipExpiry;
        this.lastDate = lastDate;
        this.scholarshipPercent = scholarshipPercent;
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
     * @return Date return the dateOfBirth
     */
    public Date getScholarshipExpiry() {
        return scholarshipExpiry;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setScholarshipExpiry(Date scholarshipExpiry) {
        this.scholarshipExpiry = scholarshipExpiry;
    }

    /**
     * @return Date return the dateOfBirth
     */
    public Date getLastDate() {
        return lastDate;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
   
    /**
     * @return String return the state
     */
    public int getScholarshipPercent() {
        return scholarshipPercent;
    }

    /**
     * @param state the state to set
     */
    public void setScholarshipPercent(int scholarshipPercent) {
        this.scholarshipPercent = scholarshipPercent;
    }
}