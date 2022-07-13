package com.opencode.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artem
 */
public class NoSuchCountExeption extends RuntimeException{
    public NoSuchCountExeption(String message) {
        super(message);
    }
}
