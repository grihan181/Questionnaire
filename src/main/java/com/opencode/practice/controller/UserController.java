package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping
    public List<Questionnaire> getQuestionnaireList() {

        return null;
    }

    @GetMapping("{id}")
    public Questionnaire getQuestionaryById(@PathVariable long id) {

        return null;
    }

    @PostMapping("{id}")
    public void saveAnswers(@PathVariable long id,
                                @RequestBody ArrayList<Integer> answers) {

    }

/*
    @PostMapping
    public void login() {

    }

    @PostMapping
    public void logout() {

    }
*/

}
