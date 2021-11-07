package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.User;

public interface UserDao {
    public User save(User user);

    public List<User> getAll();

    public User get(String emailAddress);

    public String getPassword(String emailAddress);

    public boolean exists(String emailAddress);

    public User findByEmailAddress(String emailAddress);

    public void changePassword(String emailAddress, String password);

    public User setLoginTimestamp(User user);

    public void setRole(User user, String role);

    public void update(User user);
    
    public void verifyEmail(String emailAddress);

    public void delete(String emailAddress);
}
