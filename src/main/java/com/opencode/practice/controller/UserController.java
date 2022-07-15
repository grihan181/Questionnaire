package com.opencode.practice.controller;

import com.opencode.practice.assistClass.QuestionnaireScore;
import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.exception.NoSuchCountExeption;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.projection.QuestionnaireView;
import com.opencode.practice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Grihan
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "questionnaire")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<QuestionnaireView> getQuestionnaireList() {
        return userService.findAllQuestionnaire();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Questionnaire getQuestionaryById(@PathVariable long id) {
        if (userService.getQuestionnaireById(id) == null)
            throw new NoSuchCountExeption("incorrect Data");
        else
            return userService.getQuestionnaireById(id);


    }

    @PostMapping("{userId}/{questionnaireId}")
    @PreAuthorize("hasAuthority('developers:read')")
    public void saveAnswers(@PathVariable long userId,
                            @PathVariable long questionnaireId,
                            @RequestBody List<Integer> answers) {
        userService.saveAnswers(answers, questionnaireId, userId);
    }

    @PutMapping("{userId}/{questionnaireId}")
    @PreAuthorize("hasAuthority('developers:read')")
    public void updateAnswers(@PathVariable long userId,
                              @PathVariable long questionnaireId,
                              @RequestBody List<Integer> answers) {
        userService.updateAnswers(answers, questionnaireId, userId);
    }

    @GetMapping("userscore/{userId}")
    @PreAuthorize("hasAuthority('developers:read')")
    List<QuestionnaireScore> getUserScoreInAllQuestionnaires(@PathVariable long userId) {
        return userService.getUserScoreInAllQuestionnaires(userId);
    }

    @GetMapping("leaderBoard/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<UserScore> getLeaderBoard(@PathVariable long id) {
        return userService.getLeaderBordInOneQuestionnaire(id);
    }
}
