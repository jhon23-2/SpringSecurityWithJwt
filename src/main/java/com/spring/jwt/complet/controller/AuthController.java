package com.spring.jwt.complet.controller;

import com.spring.jwt.complet.controller.dto.AuthRequestRegister;
import com.spring.jwt.complet.controller.dto.AuthRequestResponse;
import com.spring.jwt.complet.service.interface_.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthRequestResponse> register(@RequestBody AuthRequestRegister authRequestRegister){
        return new ResponseEntity<>(this.authService.register(authRequestRegister), HttpStatus.CREATED);
    }

}
