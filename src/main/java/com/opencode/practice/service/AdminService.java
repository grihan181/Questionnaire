package com.opencode.practice.service;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import com.opencode.practice.projection.Statistics;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Grihan
 */
@Service
public interface AdminService {
    List<User> findAllUsers();
    void deleteQuestionnaireById(long id);
    void addQuestionnaire(Questionnaire questionnaire);
    void editQuestionnaire(long id, Questionnaire newQcuestionnaire);
    List<Statistics> getUsersStatistics(long questionFirstId, long questionSecondId);

}
