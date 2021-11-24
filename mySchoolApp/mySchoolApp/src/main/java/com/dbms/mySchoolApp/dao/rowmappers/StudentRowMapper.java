package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
        student.setUser(user);

        return student;
    }
}