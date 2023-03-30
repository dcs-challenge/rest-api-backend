package com.dcs.backend.controller;

import com.dcs.backend.entity.AuthRequest;
import com.dcs.backend.entity.AuthResponse;
import com.dcs.backend.service.UserService;
import com.dcs.backend.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    @Autowired
    private JWTUtility jwtutility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String grantAccess(){
        return "Logged in Successfully";
    }

    @PostMapping("/auth")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(),
                        authRequest.getPassword()
                )
        );

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        final String token = jwtutility.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
