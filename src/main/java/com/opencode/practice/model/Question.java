package com.opencode.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    private int rightAnswerIdx;

/*  @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;*/

    @OneToMany/*(mappedBy = "question")*/
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new LinkedList<>();
    //    private Stack<Answer> answers = new Stack<>();
}
