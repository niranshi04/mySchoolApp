package com.dbms.mySchoolApp.models;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class AttendanceList {

    private List<Attendance> student;

    
    
    public AttendanceList() {
    	student = new ArrayList<Attendance>();
    }


    public AttendanceList( List<Attendance> student) {
        this.student = student;
       
    }

    /**
     * @return String return the name
     */
    public List<Attendance> getStudent() {
        return student;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setStudent(List<Attendance> student) {
        this.student = student;
    }
    
    
}
