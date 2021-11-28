package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.TeacherSalary;

public interface TeacherSalaryDao {
    public void save(TeacherSalary teacherSalary);

    public TeacherSalary get(int teacherId);

    public void update(TeacherSalary teacherSalary);

    public void delete(int teacherId);
}