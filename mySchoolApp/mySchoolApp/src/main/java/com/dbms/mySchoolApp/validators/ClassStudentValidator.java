package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.dao.ClassStudentDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClassStudentValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassDetailsDao classDetailsDao;
    @Autowired
    private ClassStudentDao classStudentDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ClassStudent classStudent = (ClassStudent) target;
    	if(classStudent.getClassDetails().getSection().length()<0 || classStudent.getClassDetails().getSection().length()>=3) {
    		 errors.rejectValue("classDetails.section", "Invalid.section");
    	}
    }
    public void validateClass(Object target, Errors errors) {
    	ClassStudent classStudent = (ClassStudent) target;
    	if(classStudent.getClassDetails().getSection().length()<0 || classStudent.getClassDetails().getSection().length()>=3) {
    		 errors.rejectValue("classDetails.section", "Invalid.section");
    	}
    	else {
    		if(classStudent.getStartYear()<1995 || classStudent.getStartYear()>2999) {
    			errors.rejectValue("startYear", "Invalid.year");
    		}
    		else
    		if(classDetailsDao.get(classStudent.getClassDetails().getClassNo(),classStudent.getClassDetails().getSection())==null) {
    			System.out.println("hello");
    			errors.rejectValue("classDetails.classNo", "Invalid.class");
    		}
    	}
    }
   
    public void validateStudent(Object target, Errors errors) {
    	ClassStudent classStudent = (ClassStudent) target;
    	if(studentDao.get(classStudent.getStudent().getRegistrationNo())==null) {
    		errors.rejectValue("student.registrationNo", "Invalid.registrationNo");
    	}
    	else {
    		if((classStudentDao.getClassOfStudent(classStudent.getStudent().getRegistrationNo(),classStudent.getStartYear()))!=null){
    			errors.rejectValue("student.registrationNo", "Invalid.student");
    		}
    	}
    }
   
}