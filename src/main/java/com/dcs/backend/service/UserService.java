package com.dcs.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private String user;
    private String password;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (System.getenv("DCS_USER_NAME") != null)
            user = System.getenv("DCS_USER_NAME");
        else
            user = "admin";

        if (System.getenv("DCS_PASSWORD") != null)
            password = System.getenv("DCS_PASSWORD");
        else
            password = "password";

        return new User(user, password, new ArrayList<>());
    }
}
