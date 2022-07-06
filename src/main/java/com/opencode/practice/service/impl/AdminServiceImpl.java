package com.opencode.practice.service.impl;

import com.opencode.practice.exception.QuestionnaireNotFoundException;
import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    QuestionnaireRepo questionnaireRepo;

    @Override
    public List<AppUser> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteQuestionnaireById(long id) {
        questionnaireRepo.deleteById(id);
    }

    @Override
    public void addQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepo.save(questionnaire);
    }

    @Override
    public List<Questionnaire> finAllQuestionnaire() {
        return questionnaireRepo.findAll();
    }

    @Override
    public void editQuestionnaire(long id, Questionnaire newQcuestionnaire) {

        questionnaireRepo.findById(id).map(questionnaire -> {
            questionnaire.setTitle(newQcuestionnaire.getTitle());
            for(int i = 0; i < questionnaire.getQuestions().size(); i ++) {
                questionnaire.getQuestions().get(i).setText(newQcuestionnaire.getQuestions().get(i).getText());
                questionnaire.getQuestions().get(i).setRightAnswerIdx(newQcuestionnaire.getQuestions().get(i).getRightAnswerIdx());

                for (int j = 0; j < questionnaire.getQuestions().get(i).getAnswers().size(); j++) {
                    questionnaire.getQuestions().get(i).getAnswers().get(j).setText(newQcuestionnaire.getQuestions().get(i).getAnswers().get(j).getText());
                }
            }
            return questionnaireRepo.save(questionnaire);
        }).orElseThrow(() -> new QuestionnaireNotFoundException(id));
    }//
}
