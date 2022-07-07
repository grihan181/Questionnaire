package com.opencode.practice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

/*    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;*/

    @JsonManagedReference
    @ManyToMany(mappedBy = "answers")
    private List<AppUser> appUsers = new LinkedList<>();
}
