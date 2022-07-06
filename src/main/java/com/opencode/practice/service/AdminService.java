package com.opencode.practice.service;

import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Questionnaire;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<AppUser> findAllUsers();

    void deleteQuestionnaireById(long id);

    void addQuestionnaire(Questionnaire questionnaire);

    List<Questionnaire> finAllQuestionnaire();

    Questionnaire editQuestionnaire(Questionnaire questionnaire);
}
