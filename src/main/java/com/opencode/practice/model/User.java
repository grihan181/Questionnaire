package com.opencode.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "AppUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String username;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;


    @ManyToMany
    @JoinTable(
            name = "UsersAnswer",
            joinColumns = {@JoinColumn(name = "appUser_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")}
    )
    private List<Answer> answers = new LinkedList<>();
}
