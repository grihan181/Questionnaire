package com.opencode.practice.repos;

import com.opencode.practice.model.Answer;
import com.opencode.practice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer,Long> {
}
