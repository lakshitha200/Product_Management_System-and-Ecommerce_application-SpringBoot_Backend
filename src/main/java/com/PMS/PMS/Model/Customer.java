package com.PMS.PMS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;
    @Column(nullable = false, unique = true)
    private Long cusNumber;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    private String location;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private int phone;
    private String img;
    private String role = "ROLE_CUSTOMER";



}

