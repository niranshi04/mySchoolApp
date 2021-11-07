package com.dbms.mySchoolApp.dao;

import com.dbms.mySchoolApp.models.UserToken;

public interface UserTokenDao {
    public void save(UserToken userToken);

    public String getEmailAddressByToken(String token);

    public String getTokenByUserId(String emailAddress);

    public void update(UserToken userToken);

    public void delete(String token);
}