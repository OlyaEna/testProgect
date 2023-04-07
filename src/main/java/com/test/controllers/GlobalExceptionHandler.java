package com.test.controllers;

import com.test.exceptions.ExceptionResponse;
import com.test.exceptions.InvalidStatusException;
import com.test.exceptions.NonUniqueEmailException;
import com.test.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse catchInvalidDateException(InvalidStatusException exception) {
        return new ExceptionResponse(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse catchUserNotFoundException(UserNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NonUniqueEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse catchNonUniqueEmailException(NonUniqueEmailException exception) {
        return new ExceptionResponse(exception.getMessage(), LocalDateTime.now());
    }
}