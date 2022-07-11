package com.opencode.practice.controller;

import com.opencode.practice.projection.QuestionnaireView;
import com.opencode.practice.assistClass.QuestionnaireScore;
import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<QuestionnaireView> getQuestionnaireList() {
        return userService.findAllQuestionnaire();
    }

    @GetMapping("{id}")
    public Questionnaire getQuestionaryById(@PathVariable long id) {
        return userService.getQuestionnaireById(id);
    }

    @PostMapping("{userId}/{questionnaireId}")
    public void saveAnswers(@PathVariable long userId,
                                @PathVariable long questionnaireId,
                                @RequestBody List<Integer> answers) {
        userService.saveAnswers(answers, questionnaireId, userId);
    }
    @PutMapping("{userId}/{questionnaireId}")
    public void updateAnswers(@PathVariable long userId,
                            @PathVariable long questionnaireId,
                            @RequestBody List<Integer> answers) {
        userService.updateAnswers(answers, questionnaireId, userId);
    }
    @GetMapping("userscore/{userId}")
    List<QuestionnaireScore> getUserScoreInAllQuestionnaires(@PathVariable long userId) {
        return userService.getUserScoreInAllQuestionnaires(userId);
    }

    @GetMapping("leaderBoard/{id}")
    public List<UserScore> getLeaderBoard(@PathVariable long id) {
        return userService.getLeaderBordInOneQuestionnaire(id);
    }


    @PostMapping("login")
    public void login() {

    }

    @PostMapping("logout")
    public void logout() {

    }
}
