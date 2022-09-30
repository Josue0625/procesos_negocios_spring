package com.procesos.negocio.josue.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 300, nullable = false)
    private String lastname;
    @Column(length = 20, nullable = false)
    private String document;
    @Column(length = 100)
    private String direction;
    private Date Date_Of_Birth;
    @Column(length = 20)
    private String phone;

}
