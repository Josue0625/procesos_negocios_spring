package com.procesos.negocio.josue.controllers;

import com.procesos.negocio.josue.models.User;
import com.procesos.negocio.josue.services.UserService;
import com.procesos.negocio.josue.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody User user){
        return userService.login(user.getEmail(),user.getPassword());
    }

}
