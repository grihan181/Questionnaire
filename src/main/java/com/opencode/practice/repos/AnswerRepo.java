package com.opencode.practice.repos;

import com.opencode.practice.assistClass.AnswerScore;
import com.opencode.practice.model.Answer;
import com.opencode.practice.projection.AnswerIdOnly;
import com.opencode.practice.projection.AnswerView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Grihan
 */
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
    List<AnswerIdOnly> findAnswersInOneQuestionnaireByUserId(long  Id, long questionnaireId);


    @Query(nativeQuery = true, value = "WITH first_question AS (" +
            " SELECT app_user_id, answer_id, a.text FROM users_answer ua join answer a on ua.answer_id  = a.id   WHERE a.question_id = 73), " +
            " second_question AS (SELECT app_user_id, answer_id, a.text FROM users_answer ua JOIN answer a on ua.answer_id  = a.id   WHERE a.question_id = 76)," +
            " joining AS (select first_question.app_user_id, first_question.answer_id AS firstAnswer," +
            " first_question.text AS firstQText, second_question.answer_id as secondAnswer, second_question.text AS secondQText" +
            " FROM first_question FULL JOIN second_question " +
            " ON first_question.app_user_id = second_question.app_user_id)" +
            " SELECT firstanswer, firstQText,  secondanswer,secondQText, count(*) as user_count" +
            " FROM joining GROUP BY firstanswer, secondanswer, firstQText,secondQText ORDER BY user_count desc")
    List<AnswerScore> findUsersStatistics(long questionFirstId, long questionSecondId);
}
