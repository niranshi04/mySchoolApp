package com.dbms.mySchoolApp.dao;

import java.util.List;

import com.dbms.mySchoolApp.models.FeeDetails;

public interface FeeDetailsDao {
    public void save(FeeDetails feeDetails);

    public List<FeeDetails> getAll();

    public FeeDetails get(int classNo, int year, String month);
    
    public List<FeeDetails> getClasswise(int year);

    public void delete(int classNo, int year, String month);
    
}