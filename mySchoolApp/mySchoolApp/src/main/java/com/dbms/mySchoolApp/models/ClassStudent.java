package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class ClassStudent {

    private Student student;

    private ClassDetails classDetails;


    private int startYear;

    
    
    public ClassStudent() {

    }


    public ClassStudent(Student student, ClassDetails classDetails, int startYear) {
        this.student = student;
        this.classDetails = classDetails;
        this.startYear = startYear;
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
     * @return String return the address
     */
    public ClassDetails getClassDetails() {
        return classDetails;
    }

    /**
     * @param address the address to set
     */
    public void setClassDetails(ClassDetails classDetails) {
        this.classDetails = classDetails;
    }

    /**
     * @return String return the email
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * @param email the email to set
     */
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    
}
