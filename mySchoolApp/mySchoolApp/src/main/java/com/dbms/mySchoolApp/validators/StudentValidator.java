package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Student student = (Student) target;
    	User user = student.getUser();
        if (studentDao.getStudentIdByEmailAddress(user.getEmailAddress()) != null) {
        	System.out.println(user);
            errors.rejectValue("user.emailAddress", "Duplicate.user.emailAddress");
        }

    }
   
    public void validateExisting(Object target, Errors errors) {
        Student student = (Student) target;
        User user = student.getUser();
        String email = user.getEmailAddress();
    	System.out.println(user.getEmailAddress());
    	
        if (studentDao.getStudentIdByEmailAddress(email) == null) {
        	System.out.println("j");
            errors.rejectValue("user.emailAddress", "NotExist.user.emailAddress");
        }
    }
    public void validateExistingRole(Object target, Errors errors) {
        Student student = (Student) target;
        User user = student.getUser();
        String email = user.getEmailAddress();
        if(userDao.findByEmailAddress(email)!=null && !(userDao.findByEmailAddress(email).getRole().equals("ROLE_STUDENT"))) {
        	errors.rejectValue("user.emailAddress", "DifferentRole.user.emailAddress");
        }
    }
}