package com.opencode.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artem
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "HUI")

public class QuestionnaireNotFoundException extends RuntimeException{
    public QuestionnaireNotFoundException(long id) {
        super("Could not find the questionnaire " + id);
    }
}
