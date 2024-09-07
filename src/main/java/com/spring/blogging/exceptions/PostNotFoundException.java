package com.spring.blogging.exceptions;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(String message) {
        super(message);
    }
}
