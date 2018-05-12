package com.company.exception;

public class NotToBeColleaguesException extends BaseException {

    public NotToBeColleaguesException() {
        super(108, "You cannot connect this child in a colleague relation");
    }
}
