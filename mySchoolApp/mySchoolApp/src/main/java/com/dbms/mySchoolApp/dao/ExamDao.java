package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.Exam;
import com.dbms.mySchoolApp.models.Subject;

public interface ExamDao {
    public void save(Subject subject);

    public List<Subject> getAll();

    public Exam get(int examId);
    
    public void delete(int examId);
    
}