package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionnaireRepo questionnaireRepo;
    @GetMapping
    public List<Questionnaire> getQuestionnaireList() {

        return questionnaireRepo.findAll() ;
    }

    @GetMapping("{id}")
    public Questionnaire getQuestionaryById(@PathVariable long id) {

        return null;
    }

    @PostMapping("{userId}/{questionaireId}")
    public void saveAnswers(@PathVariable long userId,
                                @PathVariable long questionaireId,
                                @RequestBody ArrayList<Integer> answers) {

    }


    @PostMapping("login")
    public void login() {

    }

    @PostMapping("logout")
    public void logout() {

    }
}
