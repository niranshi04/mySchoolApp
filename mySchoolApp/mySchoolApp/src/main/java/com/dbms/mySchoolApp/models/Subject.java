package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;

public class Subject {
    @NotEmpty
    private String subjectName;
    
    @NotEmpty
    private int subjectId;
    
    private String description;
    
    
    public Subject() {

    }


    public Subject(int subjectId, String description, String subjectName) {
        this.subjectId = subjectId;
        this.description = description;
        this.subjectName = subjectName;
      
    }

    /**
     * @return String return the name
     */
    public int getSubjectId() {
        return subjectId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    
    

    /**
     * @return String return the address
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param address the address to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return String return the address
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param address the address to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
