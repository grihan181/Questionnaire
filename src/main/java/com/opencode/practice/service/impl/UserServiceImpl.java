package com.opencode.practice.service.impl;

import com.opencode.practice.model.Question;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.AnswerRepo;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private AnswerRepo answerRepo;

    @Override
    public List<Questionnaire> findAllQuestionnaire() {
        return questionnaireRepo.findAll();
    }


    @Override
    public void saveAnswers(List<Integer> answers, long questionaireId, long userId) {
        Questionnaire questionnaire = questionnaireRepo.findById(questionaireId).get();
        List<Question> questions = questionnaire.getQuestions();
        HashMap<Long, Integer> questionsAndAnswers = new HashMap<>();

        for(int i = 0; i < answers.size(); i++) {
            questionsAndAnswers.put(questions.get(i).getId(), answers.get(i));
        }
    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        return questionnaireRepo.findById(id).get();
    }

}
