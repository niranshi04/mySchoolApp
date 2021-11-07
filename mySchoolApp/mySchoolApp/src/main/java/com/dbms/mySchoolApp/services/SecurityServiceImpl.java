package com.dbms.mySchoolApp.services;

import java.util.Objects;

import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.models.MyUserDetails;
import com.dbms.mySchoolApp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserDao userDao;

    @Override
    public String findLoggedInEmailAddress() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            return ((MyUserDetails) myUserDetails).getUser().getEmailAddress();
        }
        return null;
    }

    @Override
    public String findLoggedInUserRole() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            String role = ((MyUserDetails) myUserDetails).getUser().getRole();
            return role;
        }
        return null;
    }

    @Override
    public User findLoggedInUser() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            User user = ((MyUserDetails) myUserDetails).getUser();
            return user;
        }
        return null;
    }

    @Override
    public void autoLogin(String emailAddress, String password) {
        MyUserDetails myUserDetails = userDetailsService.loadUserByUsername(emailAddress);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                myUserDetails, password, myUserDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public void autoLogout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public boolean isUserDeletedOrUpdated() {
        String emailAddress = findLoggedInEmailAddress();
        if (emailAddress == null)
            return false;
        User user = userDao.get(emailAddress);
        if (user == null) {
            autoLogout();
            return true;
        } else {
            // Resetting the authentication object
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails oldUserDetails = (MyUserDetails)auth.getPrincipal();
            MyUserDetails newUserDetails = new MyUserDetails(user);
            boolean updated = !Objects.equals(user, oldUserDetails.getUser());
            if (updated) {
                Authentication newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, auth.getCredentials(),
                        newUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
                return true;
            }
        }
        return false;
    }
}