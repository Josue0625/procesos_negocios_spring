package com.procesos.negocio.josue.controllers;

import com.procesos.negocio.josue.models.User;
import com.procesos.negocio.josue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/user/{id}")
    public Optional <User> getUsuario(@PathVariable Long id){
        Optional <User> user= userRepository.findById(id);
        return user;
    }
    @PostMapping("/user")
    public User CreateUser(@RequestBody User user){

        userRepository.save(user);

        return user;
    }

    @GetMapping("/listusers")
    public List<User> listUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/name/{name}")
    public List<User> listByName(@PathVariable String name){
        return userRepository.findAllByName(name);
    }
    @GetMapping("/user/lastname/{lastname}")
    public List<User> listByLastname(@PathVariable String lastname){
        return userRepository.findAllByLastname(lastname);
    }
    @GetMapping("/user/{name}/{lastname}")
    public List<User> listByNameAndLastname(@PathVariable String name, @PathVariable String lastname){
        return userRepository.findAllByNameAndLastname(name,lastname);
    }
    @PutMapping("/user/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User user){
        User userBD= userRepository.findById(id).get();
        try{
            userBD.setName(user.getName());
            userBD.setLastname(user.getLastname());
            userBD.setDirection(user.getDirection());
            userBD.setDocument(user.getDocument());
            userBD.setDate_Of_Birth(user.getDate_Of_Birth());
            userBD.setPhone(user.getPhone());
            userRepository.save(userBD);
            return userBD;
        }catch(Exception e){
            return null;
        }
    }
    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable Long id){
        User userBD= userRepository.findById(id).get();
        try{
            userRepository.delete(userBD);
            return userBD;
        }catch(Exception e){
            return null;
        }
    }
}
