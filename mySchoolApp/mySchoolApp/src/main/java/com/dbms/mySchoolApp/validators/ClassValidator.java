package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClassValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassDetailsDao classDetailsDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ClassDetails classDetails = (ClassDetails) target;
    	if(classDetails.getSection().length()<0 || classDetails.getSection().length()>=3) {
    		 errors.rejectValue("section", "Invalid.section");
    	}
    }
    public void validateClass(Object target, Errors errors) {
    	ClassDetails classDetails = (ClassDetails) target;
    	if(classDetails.getSection().length()<0 || classDetails.getSection().length()>=3) {
    		 errors.rejectValue("section", "Invalid.section");
    	}
    	else {
    		if(classDetailsDao.get(classDetails.getClassNo(),classDetails.getSection())==null) {
    			 errors.rejectValue("classNo", "Invalid.class");
    		}
    	}
    }
   
}