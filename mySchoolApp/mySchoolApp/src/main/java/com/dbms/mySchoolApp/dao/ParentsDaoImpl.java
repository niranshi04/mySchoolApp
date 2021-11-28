package com.dbms.mySchoolApp.dao;

import com.dbms.mySchoolApp.models.Parents;
import com.dbms.mySchoolApp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ParentsDaoImpl implements ParentsDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Parents get(int registrationNo) {
    	try {
            String sql = "SELECT * FROM parents WHERE registrationNo = ?";
            Parents parents = (Parents)template.queryForObject(sql, new BeanPropertyRowMapper<>(Parents.class), new Object[] { registrationNo });
            return parents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void save(Parents parents) {
        String sql = "INSERT INTO parents (registrationNo, motherName, motherJob, fatherName, fatherJob) "
                + "VALUES (?, ?, ?, ?, ?)";
        template.update(sql, parents.getRegistrationNo(), parents.getMotherName(), parents.getMotherJob(),
        		parents.getFatherName(), parents.getFatherJob());
    }

    @Override
    public String getMotherName(int registrationNo) {
        try {
            String sql = "SELECT motherName FROM parents WHERE registrationNo = ?";
            String motherName = template.queryForObject(sql,  String.class, new Object[] { registrationNo });
            return motherName;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public String getFatherName(int registrationNo) {
        try {
            String sql = "SELECT fatherName FROM parents WHERE registrationNo = ?";
            String fatherName = template.queryForObject(sql,  String.class, new Object[] { registrationNo });
            return fatherName;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except studentId
     */
    @Override
    public void update(Parents parents) {
        String sql = "UPDATE parents SET motherName = ?, motherJob = ?, fatherName = ?, fatherJob = ? WHERE registrationNo = ?";
        template.update(sql, parents.getMotherName(), parents.getMotherJob(), parents.getFatherName(),
        		parents.getFatherJob(), parents.getRegistrationNo());
    }

}
