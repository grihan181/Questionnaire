package com.opencode.practice.service.impl;

import com.opencode.practice.assistClass.QuestionnaireScore;
import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.model.Answer;
import com.opencode.practice.model.Question;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import com.opencode.practice.projection.AnswerIdOnly;
import com.opencode.practice.projection.QuestionnaireView;
import com.opencode.practice.projection.UserView;
import com.opencode.practice.repos.AnswerRepo;
import com.opencode.practice.repos.QuestionRepo;
import com.opencode.practice.repos.QuestionnaireRepo;
import com.opencode.practice.repos.UserRepo;
import com.opencode.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с сервисами юзера
 * @author Grihan
 */
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

    /**
     * @author Grihan
     * @return возвращает все анкеты
     */
    @Override
    public List<QuestionnaireView> findAllQuestionnaire() {
        return questionnaireRepo.findAllQuestionnairesAsQuestionnaireView();
    }

    /**
     * @author Grihan
     * @param answers
     * @param questionnaireId
     * @param userId
     */
    @Override
    public void saveAnswers(List<Integer> answers, long questionnaireId, long userId) {
        User user = userRepo.findById(userId).get();

        user.getAnswers().addAll(undestandingUserAnswers(answers, questionnaireId));
        userRepo.save(user);
    }

    /**
     * @author Grihan
     * @param answers
     * @param questionnaireId
     * @param userId
     */
    @Override
    public void updateAnswers(List<Integer> answers, long questionnaireId, long userId) {
        User user = userRepo.findById(userId).get();

        answerRepo.deleteAnswersInUsersAnswer(userId, questionnaireId);
        user.getAnswers().addAll(undestandingUserAnswers(answers, questionnaireId));
        userRepo.save(user);
    }

    /**
     * @author Grihan
     * @param questionnaireId
     * @return возвращает анкету
     */
    @Override
    public Questionnaire getQuestionnaireById(long questionnaireId) {
        return questionnaireRepo.findById(questionnaireId).get();
    }

    /**
     * @author Grihan
     * @param questionnaireId
     * @return возвращает рейтинг пользователей
     */
    @Override
    public List<UserScore> getLeaderBordInOneQuestionnaire(long questionnaireId) {
        List<UserScore> userScores = new LinkedList<>();
        Questionnaire questionnaire = questionnaireRepo.findById(questionnaireId).get();

        List<UserView> users = userRepo.findUsersByQuestionnaireId(questionnaireId);

        List<UserScore> finalUserScores = userScores;
        users.forEach(appUser -> finalUserScores.add(new UserScore(appUser, getUserScoreInOneQuestionnaire(
                getUserAnswersInOneQuestionnaire(appUser.getId(), questionnaireId), questionnaire))));
        userScores = finalUserScores.stream()
                .sorted(Comparator.comparingInt(UserScore::getScore)
                        .reversed())
                .collect(Collectors.toList());

        return userScores;
    }

    /**
     * @author Grihan
     * @param userId
     * @return возвращает баллы пользователя во всех анкетах
     */
    @Override
    public List<QuestionnaireScore> getUserScoreInAllQuestionnaires(long userId) {
        List<QuestionnaireScore> questionnaireScores = new LinkedList<>();
        List<Questionnaire> questionnaires = questionnaireRepo.findAllQuestionnairesByUserId(userId);

        for(Questionnaire questionnaire : questionnaires) {
            questionnaireScores.add(new QuestionnaireScore(questionnaire,  getUserScoreInOneQuestionnaire(
                        getUserAnswersInOneQuestionnaire(userId, questionnaire.getId()), questionnaire)));
        }
        return questionnaireScores;
    }

    //Helping methods
    @Override
    public List<AnswerIdOnly> getUserAnswersInOneQuestionnaire(long userId, long questionnaireId) {
        return answerRepo.findAnswersInOneQuestionnaireByUserId(userId, questionnaireId);
    }

    @Override
    public int getUserScoreInOneQuestionnaire(List<AnswerIdOnly> answers, Questionnaire questionnaire) {
        int score = 0;
        for(Question question : questionnaire.getQuestions()) {
            if(answers.stream().anyMatch(answer -> answer.getId() == question.getAnswers().get(question.getRightAnswerIdx()).getId())) {
                score++;
            }
        }
        return score;
    }

    @Override
    public List<Answer> undestandingUserAnswers(List<Integer> answers, long questionnaireId) {
        List<Question> questions = questionRepo.findQuestionsByQuestionnaireId(questionnaireId);

        List<Answer> usersAnswers = new LinkedList<>();

        for(int i = 0; i < questions.size(); i++) {
            List<Answer> answersInOneQuestion = questions.get(i).getAnswers();
            usersAnswers.add(answersInOneQuestion.get(answers.get(i)));
        }
        return usersAnswers;
    }
}
