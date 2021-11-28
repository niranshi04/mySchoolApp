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
import com.dbms.mySchoolApp.models.StudentFees;
import com.dbms.mySchoolApp.models.TeacherSalary;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class TeacherSalaryDaoImpl implements TeacherSalaryDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public void save(TeacherSalary teacherSalary) {
    	Date lastDate=teacherSalary.getLastDate();
    	System.out.println(teacherSalary.getLastDate());
    	Date sqlLastDate = null;
    	Calendar calendar = new GregorianCalendar();
    	if(lastDate!=null) {
    		System.out.println("ji");
    		calendar.setTime(lastDate);
    		int year = calendar.get(Calendar.YEAR);
    		//Add one to month {0 - 11}
    		int month = calendar.get(Calendar.MONTH);
    		int day = 30;
    	    sqlLastDate = new GregorianCalendar(year,month, day).getTime();
    	}
        String sql = "INSERT INTO teachersalary (teacherId, lastDate, salary) "
                + "VALUES (?, ?, ?)";
        template.update(sql, teacherSalary.getTeacherId(), sqlLastDate, teacherSalary.getSalary());
    }

    @Override
    public TeacherSalary get(int teacherId) {
    	try {
    	String sql = "SELECT * FROM teachersalary WHERE teacherId = ?";
    	TeacherSalary teacherSalary = (TeacherSalary)template.queryForObject(sql, new BeanPropertyRowMapper<>(TeacherSalary.class), new Object[] { teacherId });
        return teacherSalary;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }

    /**
     * Update all attributes except studentId and userId
     */
    @Override
    public void update(TeacherSalary teacherSalary) {
    	Date lastDate=teacherSalary.getLastDate();
    	Date sqlLastDate = null;
    	Calendar calendar = new GregorianCalendar();
    	if(lastDate!=null) {
    		calendar.setTime(lastDate);
    		int year = calendar.get(Calendar.YEAR);
    		//Add one to month {0 - 11}
    		int month = calendar.get(Calendar.MONTH);
    		int day = 30;
    	    sqlLastDate = new GregorianCalendar(year,month, day).getTime();
    	}
        String sql = "UPDATE teachersalary SET lastDate = ?, salary = ? WHERE teacherId = ?";
        template.update(sql, sqlLastDate, teacherSalary.getSalary(), 
        		teacherSalary.getTeacherId());
    }

    @Override
    public void delete(int teacherId) {
        String sql = "DELETE FROM teachersalary WHERE teacherId = ?";
        template.update(sql, teacherId);
    }

}