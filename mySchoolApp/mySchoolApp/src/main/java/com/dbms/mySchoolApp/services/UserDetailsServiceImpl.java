package com.dbms.mySchoolApp.services;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.models.MyUserDetails;
import com.dbms.mySchoolApp.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserDao userDao;
    public MyUserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userDao.findByEmailAddress(emailAddress);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid emailAddress");
        }
        user = userDao.setLoginTimestamp(user);
        return new MyUserDetails(user);
    }

}
