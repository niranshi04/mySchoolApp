package com.dbms.mySchoolApp.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);
        Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
        teacher.setUser(user);
        return teacher;
    }
}