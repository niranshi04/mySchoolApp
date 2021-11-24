package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.Subject;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.models.Exam;
import com.dbms.mySchoolApp.models.Grades;
import com.dbms.mySchoolApp.models.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class GradesRowMapper implements RowMapper<Grades> {

    @Override
    public Grades mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ClassStudent classStudent = (new BeanPropertyRowMapper<>(ClassStudent.class)).mapRow(rs, rowNum);
        Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
        Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);
        Exam exam = (new BeanPropertyRowMapper<>(Exam.class)).mapRow(rs, rowNum);
        Grades grades = (new BeanPropertyRowMapper<>(Grades.class)).mapRow(rs, rowNum);
        Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
        grades.setTeacher(teacher);
        grades.setClassStudent(classStudent);   
        grades.setSubject(subject);  
        grades.setExam(exam);
        grades.getClassStudent().setStudent(student);
        return grades;
    }
}