package com.opencode.practice.service.impl;

import com.opencode.practice.model.Answer;
import com.opencode.practice.model.AppUser;
import com.opencode.practice.model.Question;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.AnswerRepo;
import com.opencode.practice.repos.QuestionRepo;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public List<Questionnaire> findAllQuestionnaire() {
        return questionnaireRepo.findAll();
    }

    @Override
    public void saveAnswers(List<Integer> answers, long questionaireId, long userId) {
        AppUser user = userRepo.findById(userId).get();
        List<Question> questions = questionRepo.findQuestionsByQuestionnaireId(questionaireId);
        System.out.println(questions);

        Set<Answer> usersAnswers = new HashSet<>();

        for(int i = 0; i < questions.size(); i++) {
            List<Answer> answersInOneQuestion = questions.get(i).getAnswers();
            usersAnswers.add(answersInOneQuestion.get(answers.get(i)));
        }
        System.out.println("Вывод" + usersAnswers);
        user.setAnswers(usersAnswers);
        userRepo.save(user);
    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        return questionnaireRepo.findById(id).get();
    }

}
