package com.procesos.negocio.josue.controllers;

import com.procesos.negocio.josue.models.User;
import com.procesos.negocio.josue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional <User> user= userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/user")
    public ResponseEntity CreateUser(@RequestBody User user){
        try{
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/listusers")
    public ResponseEntity listUsers(){

        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/user/name/{name}")
    public ResponseEntity listByName(@PathVariable String name){

        List<User> users =  userRepository.findAllByName(name);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/user/lastname/{lastname}")
    public ResponseEntity listByLastname(@PathVariable String lastname){

        List<User> users =  userRepository.findAllByLastname(lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/user/{name}/{lastname}")
    public ResponseEntity listByNameAndLastname(@PathVariable String name, @PathVariable String lastname){
        List<User> users =  userRepository.findAllByNameAndLastname(name,lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity editUser(@PathVariable Long id, @RequestBody User user){
        Optional <User> userBD= userRepository.findById(id);
        if(userBD.isPresent()){
            try{
                userBD.get().setName(user.getName());
                userBD.get().setLastname(user.getLastname());
                userBD.get().setDirection(user.getDirection());
                userBD.get().setDocument(user.getDocument());
                userBD.get().setDate_Of_Birth(user.getDate_Of_Birth());
                userBD.get().setPhone(user.getPhone());
                userRepository.save(userBD.get());
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        Optional<User> userBD= userRepository.findById(id);
        if(userBD.isPresent()){
            userRepository.delete(userBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
