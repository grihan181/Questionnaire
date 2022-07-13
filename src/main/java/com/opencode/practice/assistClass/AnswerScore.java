package com.opencode.practice.assistClass;

import com.opencode.practice.projection.AnswerView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Grihan
 */
@Component
@Data
@NoArgsConstructor
public class AnswerScore {
    private AnswerView answerViewFirstQ;
    private AnswerView answerViewSecondQ;

    private int score;
}
