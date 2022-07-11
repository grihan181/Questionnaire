package com.opencode.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
