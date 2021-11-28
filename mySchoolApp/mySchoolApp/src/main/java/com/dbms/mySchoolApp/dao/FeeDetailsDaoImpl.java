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
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class FeeDetailsDaoImpl implements FeeDetailsDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public void save(FeeDetails feeDetails) {
        String sql = "INSERT INTO feedetails (fees, classNo, month) "
                + "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE fees=VALUES(fees)";
        template.update(sql, feeDetails.getFees(), feeDetails.getClassNo(),
        		feeDetails.getMonth());
    }

    @Override
    public FeeDetails get(int classNo ,int year, String month) {
    	try {
    	String sql = "SELECT * FROM feedetails WHERE classNo = ? && year = ? && month = ?";
    	FeeDetails feeDetails = (FeeDetails)template.queryForObject(sql, new BeanPropertyRowMapper<>(FeeDetails.class),
    			new Object[] { classNo, year, month });
        return feeDetails;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }

    @Override
    public List<FeeDetails> getAll() {
        String sql = "SELECT * FROM feedetails ORDER BY classNo , month";
        List<FeeDetails> feeDetails = template.query(sql, new BeanPropertyRowMapper<>(FeeDetails.class));
        return feeDetails;
    }
    
    public List<FeeDetails> getClasswise(int year) {
        String sql = "SELECT classNo, JSON_OBJECTAGG(month, fees) as month_fees FROM feedetails where year = ? GROUP BY classNo ";
        List<FeeDetails> feeDetails = template.query(sql,new BeanPropertyRowMapper<>(FeeDetails.class), new Object[] { year });
        System.out.println(feeDetails.size());
        return feeDetails;
    }

    
   
    @Override
    public void delete(int classNo ,int year, String month) {
        String sql = "DELETE FROM studentfees WHERE classNo = ? && year = ? && month = ?";
        template.update(sql,classNo, year, month );
    }

}