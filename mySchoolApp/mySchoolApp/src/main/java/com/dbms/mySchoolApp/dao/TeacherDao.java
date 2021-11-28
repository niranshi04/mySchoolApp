package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.Teacher;

public interface TeacherDao {
    public Teacher save(Teacher teacher);

    public Teacher get(int teacherId);

    public Teacher getByEmailAddress(String emailAddress);

    public Integer getTeacherIdByEmailAddress(String emailAddress);

    public void update(Teacher teacher);

    public void delete(int teacherId);
}