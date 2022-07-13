package com.opencode.practice.repos;

import com.opencode.practice.model.User;
import com.opencode.practice.projection.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Grihan
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT DISTINCT au.id, au.email, au.password, au.username" +
            " FROM app_user au JOIN users_answer ua JOIN answer a JOIN question q" +
            " ON q.id = a.question_id ON a.id  = ua.answer_id on ua.app_user_id = au.id" +
            " WHERE q.questionnaire_id = ?1 ORDER BY au.id  ASC")
    List<UserView> findUsersByQuestionnaireId(long id);
}
