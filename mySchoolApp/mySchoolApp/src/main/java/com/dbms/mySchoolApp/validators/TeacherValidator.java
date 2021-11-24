package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.TeacherDao;
import com.dbms.mySchoolApp.dao.SubjectDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Subject;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Teacher teacher = (Teacher) target;
    	User user = teacher.getUser();
    	if(teacher.getSubject1() == 0 || subjectDao.get(teacher.getSubject1())==null) {
    		 errors.rejectValue("subject1", "Invalid.subject");
    	}
    	if(subjectDao.get(teacher.getSubject2())==null) {
   		 errors.rejectValue("subject2", "Invalid.subject");
   	}
    	if(subjectDao.get(teacher.getSubject3())==null) {
   		 errors.rejectValue("subject3", "Invalid.subject");
   	}
    	
    	if(!teacher.getClassType().equals("senior") && 
    			!teacher.getClassType().equals("middle") && !teacher.getClassType().equals("junior"))
    	{
    		 errors.rejectValue("classType", "Invalid.classType");
    	}
        if (teacherDao.getTeacherIdByEmailAddress(user.getEmailAddress()) != null) {
            errors.rejectValue("user.emailAddress", "Duplicate.user.emailAddress");
        }
        

    }
   
    public void validateExisting(Object target, Errors errors) {
    	Teacher teacher = (Teacher) target;
        User user = teacher.getUser();
        String email = user.getEmailAddress();
        if (teacherDao.getTeacherIdByEmailAddress(email) == null) {
            errors.rejectValue("user.emailAddress", "NotExist.user.emailAddress");
        }
    }
    public void validateExistingRole(Object target, Errors errors) {
    	Teacher teacher = (Teacher) target;
        User user = teacher.getUser();
        String email = user.getEmailAddress();
        if(userDao.findByEmailAddress(email)!=null && !(userDao.findByEmailAddress(email).getRole().equals("ROLE_TEACHER"))) {
        	errors.rejectValue("user.emailAddress", "DifferentRole.user.emailAddress");
        }
    }
}