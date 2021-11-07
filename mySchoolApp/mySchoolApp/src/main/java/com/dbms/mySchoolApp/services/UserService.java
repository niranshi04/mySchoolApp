package com.dbms.mySchoolApp.services;

import com.dbms.mySchoolApp.models.User;

public interface UserService {
    public User save(User user);

    public User findByEmailAddress(String emailAddress);
    
    public String createOrUpdateToken(String emailAddress);

    public void sendPasswordResetEmail(User user);

    public String validateToken(String token);
    
    public void sendVerificationEmail(User user, String password);
    
    public void resetPassword(String token, String password);
  
    public void verifyEmail(String token);

    public void changePassword(String emailAddress, String password);

    public boolean verifyOldPassword(String emailAddress, String password);
}