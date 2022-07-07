package com.opencode.practice.service.impl;

import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.comparator.TreeMapByValueComparator;
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

import java.util.*;

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
    @Autowired
    private TreeMapByValueComparator treeMapByValueComparator;

    @Override
    public List<Questionnaire> findAllQuestionnaire() {
        return questionnaireRepo.findAll();
    }

    @Override
    public void saveAnswers(List<Integer> answers, long questionaireId, long userId) {
        AppUser user = userRepo.findById(userId).get();
        List<Question> questions = questionRepo.findQuestionsByQuestionnaireId(questionaireId);

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

        List<Long> usersId = userRepo.findUsersByQuestionnaireId(id);

        TreeMap<Long, Integer> usersAndAnswers = new TreeMap<>();
        for(Long userId : usersId) {
            int userRightAnswers = 0;
            for(int i = 0; i < questionnaire.getQuestions().size(); i++) {
                int rightAnswer = questionnaire.getQuestions().get(i).getRightAnswerIdx();
                boolean userAnswer = questionnaire.getQuestions().get(i).getAnswers().get(rightAnswer).getAppUsers().stream().anyMatch(appUser -> appUser.getId() == userId);

                if(userAnswer) {
                    userRightAnswers++;
                }
            }
            usersAndAnswers.put(userId, userRightAnswers);
        }
        usersAndAnswers = treeMapByValueComparator.valueSort(usersAndAnswers);

        List<Long> sortedUsersId = new ArrayList<>();
        for(int i = 0; i < usersAndAnswers.size(); i++) {
            sortedUsersId.add(usersAndAnswers.keySet().toArray(new Long[usersAndAnswers.size()])[i]);
        }
        Collections.reverse(sortedUsersId);

        for(int i = 0; i < sortedUsersId.size(); i++) {
            System.out.println(sortedUsersId.get(i));
            System.out.println(usersAndAnswers.get(sortedUsersId.get(i)));
            userScores.add(new UserScore(userRepo.findById(sortedUsersId.get(i)).get(), usersAndAnswers.get(sortedUsersId.get(i))));
        }
        System.out.println(userScores);
        return userScores;
    }

}
