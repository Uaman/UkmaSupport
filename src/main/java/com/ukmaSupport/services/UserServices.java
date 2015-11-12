package com.ukmaSupport.services;

import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    private UserService userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.ukmaSupport.models.User user = userDao.getByEmail(s);
        System.out.println("Username:"+s+" role:"+user.getRole());
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        UserDetails u = new User(s, user.getPassword(), true, true, true, true, getAuthority(user.getRole()));
        return u;
    }

    public Collection<GrantedAuthority> getAuthority(String role){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
        authList.add(new GrantedAuthorityImpl("ROLE_"+role));
        return authList;
    }
}
