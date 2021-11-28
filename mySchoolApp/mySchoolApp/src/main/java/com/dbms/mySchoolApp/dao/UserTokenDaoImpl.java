package com.dbms.mySchoolApp.dao;

import com.dbms.mySchoolApp.models.UserToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserTokenDaoImpl implements UserTokenDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(UserToken userToken) {
        String sql = "INSERT INTO usertoken (token, emailAddress) VALUES (?, ?)";
        template.update(sql, userToken.getToken(), userToken.getEmailAddress());
    }

    @Override
    public String getEmailAddressByToken(String token) {
        try {
            String sql = "SELECT emailAddress FROM usertoken WHERE token = ?";
            String emailAddress = template.queryForObject(sql,String.class, new Object[] { token });
            return emailAddress;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getTokenByUserId(String emailAddress) {
        try {
            String sql = "SELECT token FROM usertoken WHERE emailAddress = ?";
            String token = template.queryForObject(sql,String.class, new Object[] { emailAddress });
            return token;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(UserToken userToken) {
        String sql = "UPDATE usertoken SET token = ? WHERE emailAddress = ?";
        template.update(sql, userToken.getToken(), userToken.getEmailAddress());
    }

    @Override
    public void delete(String token) {
        String sql = "DELETE FROM usertoken WHERE token = ?";
        template.update(sql, token);
    }

}