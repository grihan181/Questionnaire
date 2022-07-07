package com.opencode.practice.repos;

import com.opencode.practice.model.Answer;
import com.opencode.practice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Long> {
    @Query(nativeQuery = true, value = "select DISTINCT ua.app_user_id FROM users_answer ua JOIN answer a JOIN question q " +
            "ON q.id = a.question_id ON a.id  = ua.answer_id  WHERE q.questionnaire_id = ?1 ORDER BY ua.app_user_id ASC")
    List<Long> findUsersByQuestionnaireId(long id);
}
