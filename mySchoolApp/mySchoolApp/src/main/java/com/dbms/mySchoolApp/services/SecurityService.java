package com.dbms.mySchoolApp.services;

import com.dbms.mySchoolApp.models.User;

public interface SecurityService {

    public String findLoggedInEmailAddress();

    public String findLoggedInUserRole();

    public User findLoggedInUser();

    public void autoLogin(String username, String password);

    public void autoLogout();

    public boolean isUserDeletedOrUpdated();
}