package com.ukmaSupport.services;

import com.ukmaSupport.dao.interfaces.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("Username:"+s);
        com.ukmaSupport.models.User user = userDao.getByEmail(s);
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        UserDetails u = new User(s, user.getPassword(), true, true, true, true, getAuthority());
        return u;
    }

    public Collection<GrantedAuthority> getAuthority(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));
        return authList;
    }
}
