package com.dbms.mySchoolApp.validators;
import java.util.regex.*;  
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
public class NumberValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validateOp(Object target, Errors errors, String column) {
    	String number = (String) target;
    	 if (!number.matches("^[1-9][0-9]{9,9}$"))  {
    		System.out.println(number);
            errors.rejectValue(column, "wrong.phoneNumber");
        }
    }
    @Override
    public void validate(Object target, Errors errors) {
    	return;
    }
}