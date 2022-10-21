package com.procesos.negocio.josue.services;

import com.procesos.negocio.josue.models.User;
import com.procesos.negocio.josue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        try{
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<User>> allUsers() {
        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByName(String name) {
        List<User> users =  userRepository.findAllByName(name);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByLastname(String lastname) {
        List<User> users =  userRepository.findAllByLastname(lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByNameAndLastname(String name, String lastname) {
        List<User> users =  userRepository.findAllByNameAndLastname(name,lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> editUser(Long id, User user) {
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

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        Optional<User> userBD= userRepository.findById(id);
        if(userBD.isPresent()){
            userRepository.delete(userBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
