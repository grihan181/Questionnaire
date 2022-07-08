package com.opencode.practice.service;

import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.model.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Questionnaire> findAllQuestionnaire();
    void saveAnswers(List<Integer> answers, long questionnaireId, long userId);
    Questionnaire getQuestionnaireById(long id);
     List<UserScore> getLeaderBordInOneQuestion(long id) ;

    }
