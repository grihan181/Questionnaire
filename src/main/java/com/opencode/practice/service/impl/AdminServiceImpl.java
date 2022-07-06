package com.opencode.practice.service.impl;

import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    QuestionnaireRepo questionnaireRepo;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public List<AppUser> findAllUsers() {

        logger.info("Работа методаfindAllUsers ");
        return userRepo.findAll();
    }

    @Override
    public void deleteQuestionnaireById(long id) {

        logger.info("Работа deleteQuestionnaireById ");
        questionnaireRepo.deleteById(id);
    }

    @Override
    public void addQuestionnaire(Questionnaire questionnaire) {

        logger.info("Работа deleteQuestionnaireById ");
        questionnaireRepo.save(questionnaire);
    }

    @Override
    public List<Questionnaire> finAllQuestionnaire() {

        logger.info("Работа finAllQuestionnaire ");
        return questionnaireRepo.findAll();
    }

    @Override
    public Questionnaire editQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }
}
