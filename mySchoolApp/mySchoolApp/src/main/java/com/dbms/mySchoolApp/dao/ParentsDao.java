package com.dbms.mySchoolApp.dao;

import com.dbms.mySchoolApp.models.Parents;

public interface ParentsDao {
    public void save(Parents parents);
    public Parents get(int registrationNo);
    public String getFatherName(int registrationNo);
    public String getMotherName(int registrationNo);
    public void update(Parents parents);
}
