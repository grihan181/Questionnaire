package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<Questionnaire> getQuestionnaireList() {
        logger.info("работа метода getQuestionnaireList");

        return questionnaireRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Questionnaire getQuestionaryById(@PathVariable long id) {
        logger.info("работа метода getQuestionaryById");
        return null;
    }

    @PostMapping("{userId}/{questionaireId}")
    @PreAuthorize("hasAuthority('developers:read')")
    public void saveAnswers(@PathVariable long userId,
                            @PathVariable long questionaireId,
                            @RequestBody ArrayList<Integer> answers) {

    }

}
