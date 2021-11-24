package com.dbms.mySchoolApp.controllers;

import javax.validation.Valid;
import java.util.UUID;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.services.SecurityService;
import com.dbms.mySchoolApp.services.UserService;
import com.dbms.mySchoolApp.validators.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Transactional
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/admin/register")
    public String register(Model model) {
    	model.addAttribute("title", "Register Page");
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/admin/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Register Page");
            return "admin/register";
        }
        String password = UUID.randomUUID().toString();
        user.setPassword(password);
        userService.save(user);
        userService.sendVerificationEmail(user,password);
        return "redirect:/user/login?verificationEmailSent";
    }
    @GetMapping("/user/verify-email")
    public String verifyEmail(Model model, String token) {
        if (securityService.findLoggedInEmailAddress() != null) {
            return "redirect:/";
        }
        userService.verifyEmail(token);
        return "redirect:/user/login?verified";
    }
}
