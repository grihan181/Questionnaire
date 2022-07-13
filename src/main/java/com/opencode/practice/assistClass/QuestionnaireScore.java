package com.opencode.practice.assistClass;

import com.opencode.practice.model.Questionnaire;
import com.opencode.practice.projection.QuestionnaireView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author Grihan
 */
@Component
@Data
@NoArgsConstructor
public class QuestionnaireScore {
    private QuestionnaireView questionnaireView;

    private int score;

    public QuestionnaireScore(Questionnaire questionnaire, int score) {
        ProjectionFactory pf = new SpelAwareProxyProjectionFactory();

        this.questionnaireView = pf.createProjection(QuestionnaireView.class, questionnaire);
        this.score = score;
    }
}
