package com.company.exception;

public class NoAvailableException extends BaseException {

    public NoAvailableException() {
        super(105, "You cannot make these two adult couples, as it appear that one of them already has another couple");
    }
}
