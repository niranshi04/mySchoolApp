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
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class StudentFeesDaoImpl implements StudentFeesDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public void save(StudentFees studentFees) {
    	Date lastDate=studentFees.getLastDate();
    	Date sqlLastDate = null;
    	Date scholarshipExpiry=studentFees.getScholarshipExpiry();
    	Date sqlScholarshipExpiry = null;
    	Calendar calendar = new GregorianCalendar();
    	if(lastDate!=null) {
    		calendar.setTime(lastDate);
    		int year = calendar.get(Calendar.YEAR);
    		//Add one to month {0 - 11}
    		int month = calendar.get(Calendar.MONTH);
    		int day = 30;
    	    sqlLastDate = new GregorianCalendar(year,month, day).getTime();
        	
    	}
    	if(scholarshipExpiry != null) {
    		calendar.setTime(scholarshipExpiry);
    		int year1 = calendar.get(Calendar.YEAR);
    		//Add one to month {0 - 11}
    		int month1 = calendar.get(Calendar.MONTH);
    		int day1 = 30;
    		sqlScholarshipExpiry = new GregorianCalendar(year1,month1, day1).getTime();
    		
    	}
        String sql = "INSERT INTO studentfees (registrationNo, lastDate, scholarshipPercent, scholarshipExpiry) "
                + "VALUES (?, ?, ?, ?)";
        template.update(sql, studentFees.getRegistrationNo(), sqlLastDate, studentFees.getScholarshipPercent(),
        		sqlScholarshipExpiry);
    }

    @Override
    public StudentFees get(int registrationNo) {
    	try {
    	String sql = "SELECT * FROM studentfees WHERE registrationNo = ?";
    	StudentFees studentFees = (StudentFees)template.queryForObject(sql, new BeanPropertyRowMapper<>(StudentFees.class), new Object[] { registrationNo });
        return studentFees;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }

    @Override
    public List<StudentFees> getAll() {
        String sql = "SELECT * FROM studentfees";
        List<StudentFees> studentsFees = template.query(sql, new BeanPropertyRowMapper<>(StudentFees.class));
        return studentsFees;
    }

    
    /**
     * Update all attributes except studentId and userId
     */
    @Override
    public void update(StudentFees studentFees) {
    	Date lastDate=studentFees.getLastDate();
    	System.out.println(studentFees.getLastDate());
    	Date sqlLastDate = null;
    	Date scholarshipExpiry=studentFees.getScholarshipExpiry();
    	Date sqlScholarshipExpiry = null;
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
    	if(scholarshipExpiry != null) {
    		calendar.setTime(scholarshipExpiry);
    		int year1 = calendar.get(Calendar.YEAR);
    		//Add one to month {0 - 11}
    		int month1 = calendar.get(Calendar.MONTH);
    		int day1 = 30;
    		sqlScholarshipExpiry = new GregorianCalendar(year1,month1, day1).getTime();
    		
    	}
        String sql = "UPDATE studentfees SET lastDate = ?, scholarshipPercent = ?, scholarshipExpiry = ?"
                + " WHERE registrationNo = ?";
        template.update(sql, sqlLastDate, studentFees.getScholarshipPercent(),sqlScholarshipExpiry, 
        		studentFees.getRegistrationNo());
    }

    @Override
    public void delete(int registrationNo) {
        String sql = "DELETE FROM studentfees WHERE registrationNo = ?";
        template.update(sql, registrationNo);
    }

}