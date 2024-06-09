package com.nbagenmanager.spursmanager.service;

public class InvalidSearchParameterException extends RuntimeException {
    public InvalidSearchParameterException(String message) {
        super(message);
    }
}