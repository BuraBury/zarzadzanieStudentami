package com.paulinabury.demo.exceptions;

public class WrongIdException extends RuntimeException {

    public WrongIdException(String message) {
        super(message);
    }

}
