package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.Subject;

public interface SubjectDao {
    public void save(Subject subject);

    public List<Subject> getAll();

    public Subject get(int subjectId);
    
    public void delete(int subjectId);
    
}