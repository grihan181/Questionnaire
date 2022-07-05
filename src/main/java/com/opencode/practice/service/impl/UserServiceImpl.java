package com.opencode.practice.service.impl;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final QuestionnaireRepo questionnaireRepo;

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireRepo.findAll();
    }


    @Override
    public void saveAnswers(List answers, long id) {


    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        return questionnaireRepo.findById(id).get();
    }
}
