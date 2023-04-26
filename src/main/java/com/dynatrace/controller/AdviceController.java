package com.dynatrace.controller;

import com.dynatrace.exception.DateBadFormatException;
import com.dynatrace.exception.NoResultsException;
import com.dynatrace.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleHttpClientErrorException(HttpClientErrorException exception){
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(DateBadFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleDateTimeParseException(DateBadFormatException exception){
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException exception){
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(NoResultsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNoResultsException(NoResultsException exception) {
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }
}
