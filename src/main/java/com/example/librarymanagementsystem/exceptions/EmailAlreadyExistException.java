package com.example.librarymanagementsystem.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String msg) {
        super(msg);
    }
}
