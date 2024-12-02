package com.example.demoapi.exception;

public class AgeCanNotBeLowerThanZero extends Throwable {
    public AgeCanNotBeLowerThanZero(String message) {
        super(message);
    }
}
