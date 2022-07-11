package com.opencode.practice.repos;

import com.opencode.practice.model.Answer;
import com.opencode.practice.projection.AnswerIdOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer,Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete " +
            " FROM Users_answer " +
            " WHERE (app_user_id in (?1))" +
            " AND (answer_id in (SELECT id FROM answer" +
            " WHERE question_id in (SELECT id FROM question" +
            " WHERE questionnaire_id in (?2))))")
    void deleteAnswersInUsersAnswer(long userId, long questionnaireId);

    @Query(nativeQuery = true, value = "SELECT a.id" +
            " FROM app_user au JOIN users_answer ua JOIN answer a JOIN question q JOIN questionnaire qnn" +
            " ON qnn.id = q.questionnaire_id ON q.id = a.question_id ON a.id  = ua.answer_id ON ua.app_user_id = au.id" +
            " WHERE au.id  = ?1 AND qnn.id = ?2 ORDER BY a.id  ASC")
    List<AnswerIdOnly> findAnswersInOneQuestionnaireByUserId(long userId, long questionnaireId);
}
