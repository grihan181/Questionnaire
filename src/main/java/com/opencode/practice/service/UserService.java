package com.opencode.practice.service;

import com.opencode.practice.model.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Questionnaire> findAllQuestionnaire();
    void saveAnswers(List answers,long id);
    Questionnaire getQuestionnaireById(long id);


}
