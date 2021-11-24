package com.dbms.mySchoolApp.controllers;

import java.util.List;
import javax.validation.Valid;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.services.SecurityService;
import com.dbms.mySchoolApp.validators.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/user")
    public String usersPortal(Model model) {
        model.addAttribute("title", "Users Portal");
        return "user/usersPortal";
    }
    
    @GetMapping("/user/deleteUser")
    public String deleteUser(Model model) {
        model.addAttribute("title", "Delete a User");
        model.addAttribute("user", new User());
        return "user/deleteUser";
    }
    
    @PostMapping("/user/deleteUser")
    public String deleteUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
    	userValidator.validateExisting(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Delete a User");
            return "user/deleteUser";
        }
    	userDao.delete(user.getEmailAddress());
        model.addAttribute("title", "Delete a User");
        return "user/usersPortal";
    }
    
    @GetMapping("/user/viewAllUsers")
    public String viewAllUsers(Model model) {
        model.addAttribute("title", "Users List");
        model.addAttribute("message", "View all the users");
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);
        return "user/viewAllUsers";
    }
}