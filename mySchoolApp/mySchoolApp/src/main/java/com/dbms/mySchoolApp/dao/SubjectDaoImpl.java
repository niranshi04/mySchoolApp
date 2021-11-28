package com.dbms.mySchoolApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.mySchoolApp.models.Parents;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.models.Subject;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class SubjectDaoImpl implements SubjectDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public void save(Subject subject) {
        String sql = "INSERT INTO subject (subjectName, description) "
                + "VALUES (?, ?) ";
        template.update(sql, subject.getSubjectName(), subject.getDescription());
    }

    @Override
    public Subject get(int subjectId) {
    	try {
    	String sql = "SELECT * FROM subject WHERE subjectId = ?";
    	Subject subject = (Subject)template.queryForObject(sql, new BeanPropertyRowMapper<>(Subject.class),
    			new Object[] { subjectId });
        return subject;
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
    }

    @Override
    public List<Subject> getAll() {
        String sql = "SELECT * FROM subject";
        List<Subject> subject = template.query(sql, new BeanPropertyRowMapper<>(Subject.class));
        return subject;
    }
    
    @Override
    public void delete(int subjectId) {
        String sql = "DELETE FROM subject WHERE subjectId = ?";
        template.update(sql,subjectId );
    }

}