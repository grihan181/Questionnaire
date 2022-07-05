package com.opencode.practice.service;

import com.opencode.practice.model.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Questionnaire> findAll();
    void saveAnswers(List answers,long id);
    Questionnaire getQuestionnaireById(long id);


}
