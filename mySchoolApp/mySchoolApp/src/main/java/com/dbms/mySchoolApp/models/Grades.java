package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class Grades {

    private Teacher teacher;

    private ClassStudent classStudent;

    private Subject subject;
    
    private int grades;
    private Exam exam;
    
    public Grades() {
teacher = new Teacher();
classStudent = new ClassStudent();
subject = new Subject();
exam= new Exam();
    }


    public Grades(Teacher teacher, ClassStudent classStudent, Subject subject, int grades , Exam exam) {
        this.teacher = teacher;
        this.classStudent = classStudent;
        this.subject = subject;
        this.grades = grades;
        this.exam =exam;
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
    public ClassStudent getClassStudent() {
        return classStudent;
    }
    
    

    /**
     * @param address the address to set
     */
    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }

    /**
     * @return String return the email
     */
    public int getGrades() {
        return grades;
    }

    /**
     * @param email the email to set
     */
    public void setGrades(int grades) {
        this.grades = grades;
    }


    /**
     * @return String return the email
     */
    public Exam getExam() {
        return exam;
    }

    /**
     * @param email the email to set
     */
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    
}
