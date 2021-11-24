package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class Exam {
    @NotEmpty
    private String examName;
    
    @NotEmpty
    private int examId;
    
   
    
    public Exam() {

    }


    public Exam(int examId, String examName) {
        this.examId = examId;
        this.examName = examName;
    }

    /**
     * @return String return the name
     */
    public int getExamId() {
        return examId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setExamId(int examId) {
        this.examId = examId;
    }

    
    
    /**
     * @return String return the address
     */
    public String getExamName() {
        return examName;
    }

    /**
     * @param address the address to set
     */
    public void setExamName(String examName) {
        this.examName = examName;
    }
}
