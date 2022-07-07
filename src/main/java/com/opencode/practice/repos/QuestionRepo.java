package com.opencode.practice.repos;

import com.opencode.practice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM QUESTION WHERE questionnaire_id = ?1")
    List<Question> findQuestionsByQuestionnaireId(long id);
}
