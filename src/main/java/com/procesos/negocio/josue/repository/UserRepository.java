package com.procesos.negocio.josue.repository;

import com.procesos.negocio.josue.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    List<User> findAllByName(String name);
    List<User> findAllByLastname(String lastname);
    List<User> findAllByNameAndLastname(String name, String lastname);

}
