package com.company.exception;

public class TooYoungException extends BaseException {

    public TooYoungException() {
        super(102, "You cannot make friends with this young child");
    }
}
