package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.findByEmailAddress(user.getEmailAddress()) != null) {
            errors.rejectValue("emailAddress", "Duplicate.user.emailAddress");
        }
    }
    
    public void validateExisting(Object target, Errors errors) {
        User user = (User) target;
        if (userService.findByEmailAddress(user.getEmailAddress()) == null) {
            errors.rejectValue("emailAddress", "NotExist.user.emailAddress");
        }
    }
}