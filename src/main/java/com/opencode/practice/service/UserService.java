package com.opencode.practice.service;

import com.opencode.practice.Projection.UserView;
import com.opencode.practice.assistClass.QuestionnaireScore;
import com.opencode.practice.assistClass.UserScore;
import com.opencode.practice.Projection.AnswerIdOnly;
import com.opencode.practice.model.Answer;
import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<Questionnaire> findAllQuestionnaire();
    void saveAnswers(List<Integer> answers, long questionnaireId, long userId);
    void updateAnswers(List<Integer> answers, long questionnaireId, long userId);
    Questionnaire getQuestionnaireById(long questionnaireId);
    List<UserScore> getLeaderBordInOneQuestionnaire(long questionnaireId);
    List<QuestionnaireScore> getUserScoreInAllQuestionnaires(long userId);

    //Helping methods
    List<AnswerIdOnly> getUserAnswersInOneQuestionnaire(long userId, long questionnaireId);
    int getUserScoreInOneQuestionnaire(List<AnswerIdOnly> answers, Questionnaire questionnaire, UserView user);
    List<Answer> undestandingUserAnswers(List<Integer> answers, long questionnaireId);
}
