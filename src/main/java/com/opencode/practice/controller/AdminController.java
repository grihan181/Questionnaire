package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    @Autowired
    QuestionnaireRepo questionnaireRepo;

    @Autowired
    AdminService adminService;

    @PostMapping
    public void createQuestionary(@RequestBody Questionnaire questionary) {
        questionnaireRepo.save(questionary);

    }

    @DeleteMapping("{id}")
    public void deleteQuestionaty(@PathVariable long id) {
        questionnaireRepo.deleteById(id);
    }

    @PutMapping("{id}")
    public void editQuestionaty(Questionnaire questionnaire) {
        adminService.editQuestionnaire(questionnaire);
    }


}
