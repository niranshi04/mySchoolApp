package com.dbms.mySchoolApp.controllers;

import com.dbms.mySchoolApp.services.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Transactional
@Controller
public class LogoutController {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/user/logout")
    public String logout() {
        securityService.autoLogout();
        return "redirect:/user/login?logout";
    }
}
