package com.opencode.practice.projection;

import com.opencode.practice.projection.AnswerView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Grihan
 */
public interface Statistics {
     long getFirstAnswer();
     String getFirstAtext();

     long getSecondAnswer();
     String getSecondAtext();

     long getScore();
}
