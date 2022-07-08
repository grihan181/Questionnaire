package com.opencode.practice.service.impl;

import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.model.Answer;
import com.opencode.practice.model.User;
import com.opencode.practice.model.Question;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.repos.AnswerRepo;
import com.opencode.practice.repos.QuestionRepo;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public void saveAnswers(List<Integer> answers, long questionnaireId, long userId) {
        User user = userRepo.findById(userId).get();
        List<Question> questions = questionRepo.findQuestionsByQuestionnaireId(questionnaireId);

        List<Answer> usersAnswers = new LinkedList<>();

        for(int i = 0; i < questions.size(); i++) {
            List<Answer> answersInOneQuestion = questions.get(i).getAnswers();
            usersAnswers.add(answersInOneQuestion.get(answers.get(i)));
        }
        user.setAnswers(usersAnswers);
        userRepo.save(user);
    }

    @Override
    public Questionnaire getQuestionnaireById(long id) {
        return questionnaireRepo.findById(id).get();
    }

    @Override
    public List<UserScore> getLeaderBordInOneQuestion(long id) {
        List<UserScore> userScores = new LinkedList<>();
        Questionnaire questionnaire = questionnaireRepo.findById(id).get();

        List<User> users = userRepo.findUsersByQuestionnaireId(id);

        List<UserScore> finalUserScores = userScores;
        users.forEach(appUser -> {
            int userRightAnswers = 0;
            for(int i = 0; i < questionnaire.getQuestions().size(); i++) {
                int rightAnswer = questionnaire.getQuestions().get(i).getRightAnswerIdx();
                boolean userAnswer = questionnaire.getQuestions().get(i).getAnswers().get(rightAnswer).getUsers().stream().anyMatch(u -> u.getId() == appUser.getId());

                if(userAnswer) {
                    userRightAnswers++;
                }
            }
            finalUserScores.add(new UserScore(appUser, userRightAnswers));

        });
        userScores = finalUserScores.stream()
                .sorted(Comparator.comparingInt(UserScore::getScore)
                        .reversed())
                .collect(Collectors.toList());

        return userScores;
    }
}
