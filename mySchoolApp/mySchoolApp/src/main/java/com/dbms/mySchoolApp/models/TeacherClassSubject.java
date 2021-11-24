package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class TeacherClassSubject {

    private Teacher teacher;

    private ClassDetails classDetails;

    private Subject subject;
    
    private int year;
    
    public TeacherClassSubject() {
teacher = new Teacher();
classDetails = new ClassDetails();
subject = new Subject();
    }


    public TeacherClassSubject(Teacher teacher, ClassDetails classDetails, Subject subject, int year) {
        this.teacher = teacher;
        this.classDetails = classDetails;
        this.subject = subject;
        this.year = year;
    }

    /**
     * @return String return the name
     */
    public Teacher getTeacher() {
        return teacher;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    /**
     * @return String return the name
     */
    public Subject getSubject() {
        return subject;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
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
    public int getYear() {
        return year;
    }

    /**
     * @param email the email to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    
}
