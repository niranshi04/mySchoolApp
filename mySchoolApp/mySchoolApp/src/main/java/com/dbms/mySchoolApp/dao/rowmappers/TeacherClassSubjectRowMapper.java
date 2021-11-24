package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.Subject;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.TeacherClassSubject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class TeacherClassSubjectRowMapper implements RowMapper<TeacherClassSubject> {

    @Override
    public TeacherClassSubject mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ClassDetails classDetails = (new BeanPropertyRowMapper<>(ClassDetails.class)).mapRow(rs, rowNum);
        Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
        Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);
        TeacherClassSubject teacherClassSubject = (new BeanPropertyRowMapper<>(TeacherClassSubject.class)).mapRow(rs, rowNum);
        teacherClassSubject.setTeacher(teacher);
        teacherClassSubject.setClassDetails(classDetails);   
        teacherClassSubject.setSubject(subject);   
        return teacherClassSubject;
    }
}