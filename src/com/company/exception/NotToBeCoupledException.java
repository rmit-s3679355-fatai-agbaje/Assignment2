package com.company.exception;

public class NotToBeCoupledException extends BaseException {

    public NotToBeCoupledException() {
        super(106, "Only adults can become parents");
    }
}
