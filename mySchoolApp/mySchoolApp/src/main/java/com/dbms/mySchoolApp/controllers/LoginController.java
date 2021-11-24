package com.dbms.mySchoolApp.controllers;
import java.util.Date;
import java.util.GregorianCalendar;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.UserTokenDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.services.SecurityService;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;

    
    @GetMapping("/user/login") 
    public String login(Model model, String error, String logout, String verified, String resetSuccessful, String emailSent, String verificationEmailSent) {
    	if (securityService.findLoggedInEmailAddress() != null) {
            return "redirect:/";
        }
        if (error != null)
            model.addAttribute("errormessage", "Either the username and password combination is invalid, or the email is not verified");
        if (logout != null)
            model.addAttribute("successmessage", "You have been logged out successfully.");
        if (verified != null)
            model.addAttribute("successmessage", "Your email has been verified successfully.");
        if (emailSent != null)
            model.addAttribute("successmessage", "Your password reset mail has been sent");
        if (resetSuccessful != null)
            model.addAttribute("successmessage", "Your password has been reset successfully");
        if (verificationEmailSent != null)
            model.addAttribute("successmessage", "Verification email has been sent on your email address");
        model.addAttribute("title", "Login Page");
        return "user/login";
    }

    @GetMapping("/user/reset-password")
    public String resetPassword(Model model, String token) {
        if (securityService.findLoggedInEmailAddress() != null) {
            return "redirect:/";
        }
        String emailAddress = userTokenDao.getEmailAddressByToken(token);
        if (emailAddress == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token");
        }
        model.addAttribute("title", "Reset Password");
        model.addAttribute("message", "Set your password");
        model.addAttribute("submitUrl", "/user/reset-password?token=" + token);
        return "user/confirmRegistration";
    }

    @PostMapping("/user/reset-password")
    public String resetPassword(@RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword, Model model, String token) {
        if (securityService.findLoggedInEmailAddress() != null) {
            return "redirect:/";
        }
        String emailAddress = userTokenDao.getEmailAddressByToken(token);
        if (emailAddress == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token");
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("title", "Reset Password");
            model.addAttribute("message", "Set your password");
            model.addAttribute("errormessage", "The passwords do not match");
            model.addAttribute("submitUrl", "/user/reset-password?token=" + token);
            return "user/confirmRegistration";
        }
        userService.resetPassword(token, password);
        return "redirect:/user/login?resetSuccessful";
    }

    @GetMapping("/user/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        model.addAttribute("message", "Get password reset email");
        model.addAttribute("submitUrl", "/user/forgot-password");
        return "user/forgotPassword";
    }

    @PostMapping("/user/forgot-password")
    public String resetPassword(@RequestParam("email") String email, Model model) {
        if (securityService.findLoggedInEmailAddress() != null) {
            return "redirect:/";
        }
        User user = userDao.findByEmailAddress(email);
        if (user == null) {
            model.addAttribute("title", "Forgot Password");
            model.addAttribute("message", "Get password reset email");
            model.addAttribute("errormessage", "The email does not exist");
            model.addAttribute("submitUrl", "/user/forgot-password");
            return "user/forgotPassword";
        }
        userService.sendPasswordResetEmail(user);
        return "redirect:/user/login?emailSent";
    }
}
