package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.Exam;

public interface ExamDao {
    public void save(Exam exam);

    public List<Exam> getAll();

    public Exam get(int examId);
    
    public void delete(int examId);
    
}