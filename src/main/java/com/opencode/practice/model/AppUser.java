package com.opencode.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String password;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "UsersAnswer",
            joinColumns = {@JoinColumn(name = "appUser_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")}
    )
    private List<Answer> answers = new LinkedList<>();
//    private Stack<Answer> answers = new Stack<>();

}
