package com.paulinabury.demo.exceptions;

public class WrongSurnameException extends RuntimeException {

    public WrongSurnameException(String message) {
        super(message);
    }

}
