package com.paulinabury.demo.controller;

import com.paulinabury.demo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.paulinabury.demo.model.Error;

@RestControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Error wrongFieldException(WrongFieldException wrongFieldException) {
        return new Error(wrongFieldException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Error wrongIdException(WrongIdException wrongIdException) {
        return new Error(wrongIdException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Error wrongIndexNumberException(WrongIndexNumberException wrongIndexNumberException) {
        return new Error(wrongIndexNumberException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Error wrongNameException(WrongNameException wrongNameException) {
        return new Error(wrongNameException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Error wrongSurnameException(WrongSurnameException wrongSurnameException) {
        return new Error(wrongSurnameException.getMessage());
    }

}
