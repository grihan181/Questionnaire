package com.opencode.practice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

   @OneToMany(/*mappedBy = "questionnaire"*/
           cascade = CascadeType.ALL)
   @JoinColumn(name = "questionnaire_id")
   private List<Question> questions = new LinkedList<>();

}
