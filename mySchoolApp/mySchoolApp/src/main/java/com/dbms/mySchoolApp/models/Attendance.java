package com.dbms.mySchoolApp.models;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class Attendance {

    private Student student;
    private ClassStudent classStudent;
    private ClassDetails classDetails;
    private int attendance;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    
    public Attendance() {
    	student = new Student();
    	classDetails = new ClassDetails();
    	classStudent = new ClassStudent();
    }


    public Attendance(Student student, ClassDetails classDetails, int attendance , ClassStudent classStudent,Date date) {
        this.student = student;
        this.classDetails = classDetails;
        this.attendance = attendance;
        this.date = date;
        this.classStudent=classStudent;
    }

    /**
     * @return String return the name
     */
    public Student getStudent() {
        return student;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    /**
     * @return String return the name
     */
    public ClassDetails getClassDetails() {
        return classDetails;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }
    /**
     * @return String return the name
     */
    public ClassStudent getClassStudent() {
        return classStudent;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setClassDetails(ClassDetails classDetails) {
        this.classDetails = classDetails;
    }

    /**
     * @return String return the address
     */
    public int getAttendance() {
    	System.out.println(attendance);
    	return attendance;
    }
    
    

    /**
     * @param address the address to set
     */
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

   
    /**
     * @param email the email to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }

    
}
