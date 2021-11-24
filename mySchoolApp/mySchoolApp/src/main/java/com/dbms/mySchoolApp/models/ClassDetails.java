package com.dbms.mySchoolApp.models;
import javax.validation.constraints.NotEmpty;

public class ClassDetails {
    private int classId;
    
    private String section;
  
    private int classNo;
    
    public ClassDetails() {
    	System.out.println(this.classId);
    	System.out.println(this.section);
    	System.out.println(this.classNo);
    }


    public ClassDetails(int classId, String section, int classNo) {
        this.classId = classId;
        this.section = section;
        this.classNo = classNo;
    }

    /**
     * @return String return the name
     */
    public String getSection() {
        return section;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setSection(String section) {
        this.section = section;
    }

    
    /**
     * @return String return the name
     */
    public int getClassId() {
        return classId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setClassId(int classId) {
        this.classId = classId;
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

   
}
