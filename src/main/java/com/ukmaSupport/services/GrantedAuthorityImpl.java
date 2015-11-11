package com.ukmaSupport.services;

import org.springframework.security.core.GrantedAuthority;


public class GrantedAuthorityImpl implements GrantedAuthority {

    private String s;

    public GrantedAuthorityImpl(String role_user) {
        s = role_user;
    }

    @Override
    public String getAuthority() {
        return s;
    }
}
