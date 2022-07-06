package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<Questionnaire> getQuestionnaireList() {
        logger.info("работа метода getQuestionnaireList");

        return questionnaireRepo.findAll();
    }

    @GetMapping("{id}")
    public Questionnaire getQuestionaryById(@PathVariable long id) {
        logger.info("работа метода getQuestionaryById");
        return null;
    }

    @PostMapping("{userId}/{questionaireId}")
    public void saveAnswers(@PathVariable long userId,
                            @PathVariable long questionaireId,
                            @RequestBody ArrayList<Integer> answers) {

    }


    @PostMapping("login")
    public void login() {
        logger.info("работа метода login");

    }

    @PostMapping("logout")
    public void logout() {
        logger.info("работа метода logout");
    }
}
