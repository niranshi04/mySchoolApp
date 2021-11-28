package com.dbms.mySchoolApp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.utils.DateTimeUtil;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user (password, emailAddress, dateCreated, isEmailVerified, role) "
                + "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "studentId" });
                preparedStatementUtil.setParameters(preparedStatement,user.getPassword(),
                       user.getEmailAddress(), dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"),
                        user.isIsEmailVerified(), user.getRole());
                return preparedStatement;
            }
        }, keyHolder);
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User get(String emailAddress) {
        try {
            String sql = "SELECT * FROM user WHERE emailAddress = ?";
            return (User) template.queryForObject(sql, 
                    new BeanPropertyRowMapper<>(User.class),new Object[] { emailAddress });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public void verifyEmail(String emailAddress) {
        String sql = "UPDATE user SET isEmailVerified = true WHERE emailAddress = ?";
        template.update(sql, emailAddress);
    }
    
    @Override
    public String getPassword(String emailAddress) {
        try {
            String sql = "SELECT password FROM user WHERE emailAddress = ?";
            return template.queryForObject(sql, String.class, new Object[] { emailAddress });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean exists(String emailAddress) {
        String sql = "SELECT COUNT(*) FROM user WHERE emailAddress = ?";
        int count = template.queryForObject(sql, Integer.class);
        return (count > 0);
    }
    
    @Override
    public User findByEmailAddress(String emailAddress) {
        try {
            String sql = "SELECT * FROM user WHERE emailAddress = ?";
            return (User) template.queryForObject(sql,
                    new BeanPropertyRowMapper<>(User.class),new Object[] { emailAddress });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void changePassword(String emailAddress, String password) {
        String sql = "UPDATE user SET password = ? WHERE emailAddress = ?";
        template.update(sql, password, emailAddress);
    }

    @Override
    public User setLoginTimestamp(User user) {
        String lastLoginDate = dateTimeUtil.getCurrentDateTime("yyyy-MM-dd");
        String lastLoginTime = dateTimeUtil.getCurrentDateTime("HH:mm:ss");
        String sql = "UPDATE user SET lastLoginDate = ?, lastLoginTime = ? WHERE emailAddress = ?";
        template.update(sql, lastLoginDate, lastLoginTime, user.getEmailAddress());
        user.setLastLoginDate(Date.valueOf(lastLoginDate));
        user.setLastLoginTime(Time.valueOf(lastLoginTime));
        return user;
    }

    @Override
    public void setRole(User user, String role) {
        String sql = "UPDATE user SET role = ? WHERE emailAddress = ?";
        template.update(sql, role, user.getEmailAddress());
    }

    @Override
    /**
     * Update all attributes except password, dateCreated, lastLoginDate,
     * lastLoginTime, role
     */
    public void update(User user) {
        String sql = "UPDATE user SET emailAddress = ?"
                + "isEmailVerified = ? WHERE emailAddress = ?";
        template.update(sql,user.getEmailAddress(),
               false, user.getEmailAddress());
    }

    @Override
    public void delete(String emailAddress) {
        String sql = "DELETE FROM user WHERE emailAddress = ?";
        template.update(sql, emailAddress);
    }

}