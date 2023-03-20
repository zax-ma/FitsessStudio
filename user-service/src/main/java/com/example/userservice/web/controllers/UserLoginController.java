package com.example.userservice.web.controllers;

import com.example.userservice.dto.LoginDTO;
import com.example.userservice.service.user.api.IUserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/login")
public class UserLoginController {

    IUserAuthenticationService service;
    public UserLoginController(IUserAuthenticationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
        return ResponseEntity.ok("Token: " + service.login(loginDto));
    }
}