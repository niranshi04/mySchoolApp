package com.dbms.mySchoolApp.dao;

import java.util.List;
import java.util.Date;

import com.dbms.mySchoolApp.models.Attendance;

public interface AttendanceDao {
    public void save(Attendance attendance);
    public List<Attendance> getStudents(Attendance attendance);
    public List<Attendance> get(Attendance attendance);
    public void delete(int examId);
}