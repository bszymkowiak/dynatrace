package com.dynatrace.exception;

public class NoResultsException extends RuntimeException{

    public NoResultsException(String message) {
        super(message);
    }
}
