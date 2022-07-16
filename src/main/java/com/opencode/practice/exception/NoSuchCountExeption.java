package com.opencode.practice.exception;

/**
 * @author Artem
 */
public class NoSuchCountExeption extends RuntimeException{
    public NoSuchCountExeption(String message) {
        super(message);
    }
}
