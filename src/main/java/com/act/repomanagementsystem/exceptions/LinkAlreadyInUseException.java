package com.act.repomanagementsystem.exceptions;

public class LinkAlreadyInUseException extends RuntimeException {
    public LinkAlreadyInUseException(String message) {
        super(message);
    }
}
