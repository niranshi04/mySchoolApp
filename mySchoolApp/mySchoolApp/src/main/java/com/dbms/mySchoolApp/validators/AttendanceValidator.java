package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.Attendance;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AttendanceValidator implements Validator {
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
    	Attendance attendance = (Attendance) target;
    	if(attendance.getClassDetails().getSection().length()<0 || attendance.getClassDetails().getSection().length()>=3) {
    		 errors.rejectValue("classDetails.section", "Invalid.section");
    	}
    	else {
    		if(classDetailsDao.get(attendance.getClassDetails().getClassNo(),attendance.getClassDetails().getSection())==null) {
    			 errors.rejectValue("classDetails.classNo", "Invalid.class");
    		}
    		
    	}
    }
    
    
    public void validateExtras(Object target, Errors errors) {
    	Attendance attendance = (Attendance) target;
    	//System.out.println(classDetails.getSection().length());
    	Date date = attendance.getDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date1 = simpleDateFormat.format(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
    	if(year<1995  || year>2999 ) {
    		 errors.rejectValue("year", "Invalid.year");
    	}
    	else {
    		if(month<0  || month>11 ) {
       		 errors.rejectValue("month", "Invalid.month");
       	}
    		
    	}
    }
   
}