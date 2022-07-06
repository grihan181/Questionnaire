package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.service.impl.AdminServiceImpl;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @PostMapping
    public void createQuestionary(@RequestBody Questionnaire questionary) {
        adminService.addQuestionnaire(questionary);
    }

    @DeleteMapping("{id}")
    public void deleteQuestionaty(@PathVariable long id) {
        adminService.deleteQuestionnaireById(id);
    }

    @PutMapping("{id}")
    public void editQuestionaty(@PathVariable long id, @RequestBody Questionnaire questionnaire) {
            adminService.editQuestionnaire(id, questionnaire);
        }


}
