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
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;

@Transactional
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public Student save(Student student) {
        String sql = "INSERT INTO student (name, gender, dateOfBirth, houseNumber, street, city, state, dateOfAdmission, "
                + "emailAddress, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"registrationNo"});
                preparedStatementUtil.setParameters(preparedStatement, student.getName(), student.getGender(), student.getDateOfBirth(),
                        student.getHouseNumber(), student.getStreet(), student.getCity(), student.getState(),
                        student.getDateOfAdmission(), student.getUser().getEmailAddress(), student.getPhoneNumber());
                return preparedStatement;
            }
        }, keyHolder);
        int registrationNo = keyHolder.getKey().intValue();
        student.setRegistrationNo(registrationNo);
        return student;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM student NATURAL JOIN User";
        List<Student> students = template.query(sql, new StudentRowMapper());
        System.out.println(students.get(0).getGender());
        return students;
    }

    @Override
    public List<Student> getAllByCourseId(String courseId) {
        String sql = "SELECT * FROM student NATURAL JOIN user NATURAL JOIN enrollment WHERE courseId = ?";
        List<Student> students = template.query(sql,  new StudentRowMapper(), new Object[] { courseId });
       
        return students;
    }

    @Override
    public Student get(int registrationNo) {
        try {
            String sql = "SELECT * FROM student NATURAL JOIN user WHERE registrationNo = ?";
            return (Student) template.queryForObject(sql,
                    new StudentRowMapper(), new Object[] { registrationNo });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Student getByEmailAddress(String emailAddress) {
        try {
            String sql = "SELECT * FROM student NATURAL JOIN user WHERE emailAddress = ?";
            return (Student) template.queryForObject(sql, new StudentRowMapper(), new Object[] { emailAddress });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer getStudentIdByEmailAddress(String emailAddress) {
        try {
        	System.out.println("ko");
            String sql = "SELECT registrationNo FROM student WHERE emailAddress = ?";
            return template.queryForObject(sql, Integer.class, new Object[] { emailAddress });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except studentId and userId
     */
    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, gender = ?, dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ?, "
                + "dateOfAdmission = ?, dateOfLeavingSchool = ? , emailAddress = ?, phoneNumber = ? WHERE registrationNo = ?";
        template.update(sql, student.getName(), student.getGender(), student.getDateOfBirth(), student.getHouseNumber(),
                student.getStreet(), student.getCity(), student.getState(),
                student.getDateOfAdmission(), student.getDateOfLeavingSchool(),
                student.getUser().getEmailAddress(),
                student.getPhoneNumber(),
                student.getRegistrationNo());
    }

    @Override
    public void delete(int registrationNo) {
        String sql = "DELETE FROM student WHERE registrationNo = ?";
        template.update(sql, registrationNo);
    }

}