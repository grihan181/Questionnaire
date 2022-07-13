package com.opencode.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artem
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such user")
public class NoSuchCountExeption extends RuntimeException{
    public NoSuchCountExeption(String message) {
        super(message);
    }
}
