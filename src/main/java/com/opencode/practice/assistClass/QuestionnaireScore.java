package com.opencode.practice.assistClass;

import com.opencode.practice.model.Questionnaire;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class QuestionnaireScore {
    private long questionnaireId;
    private String title;

    int score;
}
