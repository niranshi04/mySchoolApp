package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class FeeDetails {
    @NotEmpty
    private int month;
    
    private int fees;
    @NotEmpty
    private int classNo;
    
    public FeeDetails() {

    }


    public FeeDetails(int year, int fees, int classNo, int month) {
        this.fees = fees;
        this.classNo = classNo;
        this.month = month;
    }

    
    
    /**
     * @return String return the name
     */
    public int getFees() {
        return fees;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setFees(int fees) {
        this.fees = fees;
    }
    
    /**
     * @return String return the name
     */
    public int getClassNo() {
        return classNo;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    /**
     * @return String return the address
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param address the address to set
     */
    public void setMonth(int month) {
        this.month = month;
    }
}
