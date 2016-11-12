package com.ukmaSupport.services;

import com.ukmaSupport.services.interfaces.UserService;
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

    private final String ACTIVE_STATUS = "active";

    @Autowired
    private UserService userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String result = email.toLowerCase();
        com.ukmaSupport.models.User user = userDao.getByEmail(result);
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        System.out.println("Username:"+result+" role:"+user.getRole());
        boolean isActive = true;
        if(!user.getAccountStatus().equals(ACTIVE_STATUS)) isActive = false;
        UserDetails u = new User(result, user.getPassword(), true, true, true, isActive, getAuthority(user.getRole()));

        return u;
    }

    public Collection<GrantedAuthority> getAuthority(String role){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
        authList.add(new GrantedAuthorityImpl("ROLE_"+role));
        return authList;
    }
}
