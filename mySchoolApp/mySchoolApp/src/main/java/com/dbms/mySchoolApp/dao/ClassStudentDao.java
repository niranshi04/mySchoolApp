package com.dbms.mySchoolApp.dao;

import java.util.List;
import java.util.Date;

import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.ClassDetails;

public interface ClassStudentDao {
    public void save(ClassStudent classStudent);

    public List<ClassStudent> getAllPresent();

    public List<ClassStudent> getAllPresentInClass(ClassDetails classDetails);
    
    public List<ClassStudent> getAllInClass(ClassDetails classDetails, int year );
    
    public List<ClassStudent> getAll(int year );
    
    public ClassStudent getPresentClassOfStudent(int registrationNo);
    
    public ClassStudent getClassOfStudent(int registrationNo, int year);
    
    public void deletePresent(int classId, int registrationNo);
    
    public void updatePresent(ClassStudent classStudent, int registrationNo);
    
    public void delete(ClassStudent classStudent);
    
    
}