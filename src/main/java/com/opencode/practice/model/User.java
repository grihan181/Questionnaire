package com.opencode.practice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PIZDA")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String usernamame;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
