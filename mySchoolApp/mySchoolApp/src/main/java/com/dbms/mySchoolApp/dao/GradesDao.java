package com.dbms.mySchoolApp.dao;

import java.util.List;
import java.util.Date;

import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.Grades;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.TeacherClassSubject;

public interface GradesDao {
    public void save(TeacherClassSubject teacherClassSubject);

    public List<ClassStudent> getAllPresentInClass(ClassDetails classDetails);
    
    public List<TeacherClassSubject> getAllInClass(ClassDetails classDetails, int year );
    
    public List<ClassStudent> getAll(int year );
    
    public ClassStudent getPresentClassOfStudent(int registrationNo);
    
    public ClassStudent getClassOfStudent(int registrationNo, int year);
    
    public void deletePresent(int classId, int registrationNo);
    
    public void updatePresent(ClassStudent classStudent, int registrationNo);
    
    public void delete(TeacherClassSubject TeacherClassSubject);
    
    public List<Grades> get(ClassStudent classStudent, int subjectId);
    
    public void save (Grades grades);
    
}