package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.Attendance;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class AttendanceRowMapper implements RowMapper<Attendance> {

    @Override
    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
        Attendance attendance = (new BeanPropertyRowMapper<>(Attendance.class)).mapRow(rs, rowNum);
        ClassDetails classDetails = (new BeanPropertyRowMapper<>(ClassDetails.class)).mapRow(rs, rowNum);
        ClassStudent classStudent = (new BeanPropertyRowMapper<>(ClassStudent.class)).mapRow(rs, rowNum);
        attendance.setClassDetails(classDetails);
        attendance.setClassStudent(classStudent);
        attendance.setStudent(student);
        return attendance;
    }
}