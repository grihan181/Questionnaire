package com.opencode.practice.service;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<User> findAllUsers();
    void deleteQuestionnaireById(long id);
    void addQuestionnaire(Questionnaire questionnaire);
    void editQuestionnaire(long id, Questionnaire newQcuestionnaire);


}
