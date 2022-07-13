package com.opencode.practice.repos;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.projection.QuestionnaireView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Grihan
 */
@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire,Long> {
    @Query(nativeQuery = true,value = "SELECT id, title FROM questionnaire")
    List<QuestionnaireView> findAllQuestionnairesAsQuestionnaireView();

    @Query(nativeQuery = true,value = "SELECT DISTINCT qnn.id, qnn.title " +
            " FROM questionnaire qnn JOIN question q JOIN answer a   JOIN users_answer ua JOIN app_user au " +
            " ON au.id = ua.app_user_id ON ua.answer_id = a.id ON a.question_id  = q.id on q.questionnaire_id = qnn.id" +
            " WHERE au.id = ?1 ORDER BY qnn.id  ASC")
    List<Questionnaire> findAllQuestionnairesByUserId(long userId);
}
