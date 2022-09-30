package com.procesos.negocio.josue.controllers;

import com.procesos.negocio.josue.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    @GetMapping(value = "/user/{id}")
    public User getUsuario(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("Josue");
        user.setLastname("Campo");
        user.setDirection("Acolsure");
        user.setDate_Of_Birth(new Date(2002,04,06));
        user.setDocument("1007561307");
        user.setPhone("3145349165");
        return user;
    }
}
