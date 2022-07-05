package com.opencode.practice.controller;

import com.opencode.practice.model.Questionnaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    @PostMapping
    public void createQuestionary(@RequestBody Questionnaire questionary) {

    }

    @DeleteMapping("{id}")
    public void deleteQuestionaty(@PathVariable long id) {

    }

    @PutMapping("{id}")
    public void editQuestionaty(@PathVariable long id) {

    }


}
