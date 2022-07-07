package com.opencode.practice.repos;

import com.opencode.practice.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire,Long> {
}
