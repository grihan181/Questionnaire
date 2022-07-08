package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepositorySecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private QuestionnaireRepo questionnaireRepo;

    @Autowired
    private UserRepositorySecurity userRepositorySecurity;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @PostMapping
    public void createQuestionary(@RequestBody Questionnaire questionary) {
        logger.info("Работа метода createQuestionary");
        questionnaireRepo.save(questionary);

    }

    @DeleteMapping("{id}")
    public void deleteQuestionaty(@PathVariable long id) {
        logger.info("Работа метода deleteQuestionaty");

        questionnaireRepo.deleteById(id);
    }

    @PutMapping("{id}")
    public void editQuestionaty(Questionnaire questionnaire) {
        logger.info("Работа метода editQuestionaty");
    }


    @GetMapping("/users")
    public List<User> users() {
        return userRepositorySecurity.findAll();
    }

}
