package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.StudentFees;

public interface StudentFeesDao {
    public void save(StudentFees studentFees);

    public List<StudentFees> getAll();

    public StudentFees get(int registrationNo);

    public void update(StudentFees studentFees);

    public void delete(int registrationNo);
}