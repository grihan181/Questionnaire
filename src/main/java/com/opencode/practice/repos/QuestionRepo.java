package com.opencode.practice.repos;

import com.opencode.practice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
//    @Query("SELECT q FROM QUESTION q WHERE q.questionnaire_id = :id")
    List<Question> findByQuestionnaireId(@Param("id") long id);
}
