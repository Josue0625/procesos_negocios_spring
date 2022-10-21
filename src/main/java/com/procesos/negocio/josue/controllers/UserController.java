package com.procesos.negocio.josue.controllers;

import com.procesos.negocio.josue.models.User;
import com.procesos.negocio.josue.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
       return userService.getUserById(id);
    }
    @PostMapping("/user")
    public ResponseEntity CreateUser(@Valid @RequestBody  User user){
        return userService.createUser(user);
    }

    @GetMapping("/listusers")
    public ResponseEntity listUsers(){
        return userService.allUsers();
    }
    @GetMapping("/user/name/{name}")
    public ResponseEntity listByName(@PathVariable String name){
        return userService.allUsersByName(name);
    }
    @GetMapping("/user/lastname/{lastname}")
    public ResponseEntity listByLastname(@PathVariable String lastname){
        return userService.allUsersByLastname(lastname);
    }
    @GetMapping("/user/{name}/{lastname}")
    public ResponseEntity listByNameAndLastname(@PathVariable String name, @PathVariable String lastname){
       return userService.allUsersByNameAndLastname(name, lastname);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity editUser(@PathVariable Long id,@Valid @RequestBody  User user){
    return userService.editUser(id, user);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
