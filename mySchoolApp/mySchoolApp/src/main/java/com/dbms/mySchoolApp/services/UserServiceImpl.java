package com.dbms.mySchoolApp.services;

import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.UserTokenDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.UserToken;
import com.dbms.mySchoolApp.utils.ServerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private ServerUtil serverUtil;

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public User save(User user) {
        if (user.getPassword() != null)
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userDao.findByEmailAddress(emailAddress);
    }

    @Override
    public void sendVerificationEmail(User user, String password) {
        String email = user.getEmailAddress();
        String subject = "Account Creation Successful | Verify Email";
        String message = "";
        String token = createOrUpdateToken(user.getEmailAddress());
        message += "Hello " + user.getEmailAddress() + ",\n\n";
        message += "Please go to " + serverUtil.getHostAddressAndPort() + "/user/verify-email?token=" + token;
        message += " to verify your email. Thereafter, you can login using your username '" + user.getEmailAddress() + "'";
        message += " and password '" + password +"' by going to " + serverUtil.getHostAddressAndPort() + "/user/login" + "\n\nThank you!";
        emailSendService.sendEmail(email, subject, message);
    }
    public String createOrUpdateToken(String emailAddress) {
        UserToken userToken = new UserToken(emailAddress);
        String token = userTokenDao.getTokenByUserId(emailAddress);
        if (token == null) {
            userTokenDao.save(userToken);
        } else {
            userTokenDao.update(userToken);
        }
        return userToken.getToken();
    }
    
    @Override
    public void sendPasswordResetEmail(User user) {
        String email = user.getEmailAddress();
        String subject = "Password Reset Email";
        String message = "";
        String token = createOrUpdateToken(user.getEmailAddress());
        message += "Hello " + user.getEmailAddress() + ",\n\n";
        message += "Please go to " + serverUtil.getHostAddressAndPort() + "/user/reset-password?token=" + token;
        message += " to reset your password. Thereafter, you can login using your emailAddress '" + user.getEmailAddress()
                + "'";
        message += " by going to " + serverUtil.getHostAddressAndPort() + "/user/login" + "\n\nThank you!";
        emailSendService.sendEmail(email, subject, message);
    }

    public String validateToken(String token) {
    	String emailAddress = userTokenDao.getEmailAddressByToken(token);
        if (emailAddress == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token");
        }
        userTokenDao.delete(token);
        return emailAddress;
    }

    @Override
    public void resetPassword(String token, String password) {
    	String emailAddress = validateToken(token);
        userDao.changePassword(emailAddress, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public void changePassword(String emailAddress, String password) {
        userDao.changePassword(emailAddress, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public boolean verifyOldPassword(String emailAddress, String password) {
        String encryptedPassword = userDao.getPassword(emailAddress);
        return bCryptPasswordEncoder.matches(password, encryptedPassword);
    }
    @Override
    public void verifyEmail(String token) {
       String emailAddress = validateToken(token);
        userDao.verifyEmail(emailAddress);
    }
}