package com.opencode.practice.service.impl;

import com.opencode.practice.exception.QuestionnaireNotFoundException;
import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Question;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.LinkedList;
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
        return null;
    }

    @Override
    public Questionnaire editQuestionnaire(Questionnaire questionnaire) {
        return null;
    }
}
