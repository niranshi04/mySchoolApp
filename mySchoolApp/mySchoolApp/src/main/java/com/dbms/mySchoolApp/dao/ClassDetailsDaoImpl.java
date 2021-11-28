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
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class ClassDetailsDaoImpl implements ClassDetailsDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;
    @Override
    public void save(ClassDetails classDetails) {
        String sql = "INSERT IGNORE INTO classdetails (classNo, section) " + "VALUES (?, ?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"classId"});
                preparedStatementUtil.setParameters(preparedStatement, classDetails.getClassNo(), classDetails.getSection());
                return preparedStatement;
            }
        }, keyHolder);
        if(keyHolder.getKey()!=null) {
        int classId =keyHolder.getKey().intValue();
        classDetails.setClassId(classId);
        }
    }
    @Override
    public ClassDetails get(int classNo ,String section) {
    	try {
    	String sql = "SELECT * FROM classdetails WHERE classNo = ? && section = ? ";
    	ClassDetails classDetails = (ClassDetails)template.queryForObject(sql, new BeanPropertyRowMapper<>(ClassDetails.class),
    			new Object[] { classNo, section });
        return classDetails;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }
    @Override
    public ClassDetails getClass(int classId) {
    	try {
    	String sql = "SELECT * FROM classdetails WHERE classId = ?";
    	ClassDetails classDetails = (ClassDetails)template.queryForObject(sql, new BeanPropertyRowMapper<>(ClassDetails.class),
    			new Object[] { classId});
        return classDetails;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }
    @Override
    public List<ClassDetails> getAll() {
    	try {
    	String sql = "SELECT * FROM classdetails";
    	List<ClassDetails> classDetails = template.query(sql, new BeanPropertyRowMapper<>(ClassDetails.class));
        return classDetails;
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }
    
    @Override
    public void delete(int classId) {
        String sql = "DELETE FROM classdetails WHERE classId = ?";
        template.update(sql,classId );
    }

  
}