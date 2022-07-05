package com.opencode.practice.service.impl;

import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepo userRepo;

    @Override
    public List<AppUser> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteQuestionnaireById(long id) {

    }

    @Override
    public void addQuestionnaire(Questionnaire questionnaire) {

    }
}
