package com.opencode.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Grihan,Artem
 */
@Entity
@Data
@NoArgsConstructor
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

   @OneToMany(cascade = CascadeType.REMOVE)
   @JoinColumn(name = "questionnaire_id")
   private List<Question> questions = new LinkedList<>();
}
