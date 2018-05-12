package com.company.exception;

public class NoParentException extends BaseException {

    public NoParentException() {
        super(104, "The newly added child must have exactly two parents");
    }
}
