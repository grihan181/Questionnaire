package com.opencode.practice.repos;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire,Long> {
}
