package com.ukmaSupport.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Dima on 09.11.2015.
 */

@Service
public class SpringSecurityEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
