package com.dbms.mySchoolApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.TeacherRowMapper;

@Transactional
@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

@Override
    public Teacher save(Teacher teacher) {
        String sql = "INSERT INTO teacher (name, gender, dateOfBirth, houseNumber, street, city, state, dateOfJoining, "
                + "emailAddress, phoneNumber, subject1, subject2, subject3, classType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                + ",?,?,?,?)";
        System.out.println("koop");
        System.out.println(teacher.getSubject2());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"registrationNo"});
                preparedStatementUtil.setParameters(preparedStatement, teacher.getName(), teacher.getGender(), teacher.getDateOfBirth(),
                		teacher.getHouseNumber(), teacher.getStreet(), teacher.getCity(), teacher.getState(),
                		teacher.getDateOfJoining(), teacher.getUser().getEmailAddress(), teacher.getPhoneNumber(),
                		teacher.getSubject1(),teacher.getSubject2(), teacher.getSubject3(),teacher.getClassType() );
                return preparedStatement;
            }
        }, keyHolder);
        int teacherId = keyHolder.getKey().intValue();
        teacher.setTeacherId(teacherId);
        return teacher;
    }


@Override
public Integer getTeacherIdByEmailAddress(String emailAddress) {
    try {
    	System.out.println("ko");
        String sql = "SELECT teacherId FROM teacher WHERE emailAddress = ?";
        return template.queryForObject(sql, Integer.class, new Object[] { emailAddress });
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}

@Override
public Teacher get(int teacherId) {
	try {
        String sql = "SELECT * FROM teacher NATURAL JOIN user WHERE teacherId = ?";
        return (Teacher) template.queryForObject(sql,
                new TeacherRowMapper(), new Object[] { teacherId });
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}
public Teacher getByEmailAddress(String emailAddress) {
	try {
        String sql = "SELECT * FROM teacher NATURAL JOIN user WHERE emailAddress = ?";
        return (Teacher) template.queryForObject(sql, new TeacherRowMapper(), new Object[] { emailAddress });
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}
@Override
public void update(Teacher teacher) {
    String sql = "UPDATE teacher SET name = ?, gender = ?, dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ?, "
            + "dateOfJoining = ?, emailAddress = ?, phoneNumber = ? ,  subject1 = ? , subject2 = ?, subject3 =? , classType =?"
            + "WHERE teacherId = ?";
    template.update(sql, teacher.getName(), teacher.getGender(), teacher.getDateOfBirth(), teacher.getHouseNumber(),
    		teacher.getStreet(), teacher.getCity(), teacher.getState(),
    		teacher.getDateOfJoining(),
    		teacher.getUser().getEmailAddress(),
    		teacher.getPhoneNumber(),
            teacher.getSubject1(),teacher.getSubject2(), teacher.getSubject3(),teacher.getClassType(),
            teacher.getTeacherId());
}

@Override
public void delete(int teacherId) {
    String sql = "DELETE FROM teacher WHERE teacherId = ?";
    template.update(sql, teacherId);
}

}