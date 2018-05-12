package com.company.exception;

public class NotToBeFriendsException extends BaseException {

    public NotToBeFriendsException() {
        super(103, "These two users cannot be friends.");
    }

}
