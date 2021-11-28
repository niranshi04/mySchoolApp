package com.dbms.mySchoolApp.models;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class TeacherSalary {
    private int teacherId;
    
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastDate;
    
    private int salary;



    public TeacherSalary() {
    }

    public TeacherSalary(int teachedId, Date lastDate, int salary) {
        this.teacherId=teachedId;
        this.lastDate = lastDate;
        this.salary = salary;
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
    public int getSalary() {
        return salary;
    }

    /**
     * @param state the state to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
}