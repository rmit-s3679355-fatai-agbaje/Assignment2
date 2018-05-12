package com.company.exception;

public class NoSuchAgeException extends BaseException {

    public NoSuchAgeException() {
        super(107, "The provided age is invalid");
    }
}
