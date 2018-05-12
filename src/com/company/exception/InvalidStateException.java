package com.company.exception;

public class InvalidStateException extends BaseException {

    public InvalidStateException(String state) {
        super(110, "The provided state "+state+" doesn't appear to be valid");
    }
}
