package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.Student;

public interface StudentDao {
    public Student save(Student student);

    public List<Student> getAll();

    public List<Student> getAllByCourseId(String courseId);

    public Student get(int studentId);

    public Student getByEmailAddress(String emailAddress);

    public Integer getStudentIdByEmailAddress(String emailAddress);

    public void update(Student student);

    public void delete(int studentId);
}