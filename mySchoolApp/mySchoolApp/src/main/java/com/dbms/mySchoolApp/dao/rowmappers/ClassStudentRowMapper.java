package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class ClassStudentRowMapper implements RowMapper<ClassStudent> {

    @Override
    public ClassStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ClassDetails classDetails = (new BeanPropertyRowMapper<>(ClassDetails.class)).mapRow(rs, rowNum);
        Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
        ClassStudent classStudent = (new BeanPropertyRowMapper<>(ClassStudent.class)).mapRow(rs, rowNum);
        classStudent.setStudent(student);
        classStudent.setClassDetails(classDetails);       
        return classStudent;
    }
}