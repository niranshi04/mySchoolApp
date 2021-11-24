package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.ClassDetails;

public interface ClassDetailsDao {
    public void save(ClassDetails classDetails);

    public List<ClassDetails> getAll();

    public ClassDetails get(int classNo, String section);
    
    public ClassDetails getClass(int classId);
    
    public void delete(int classId);
    
}