package com.social.socialapplication.exception;

public class PostNotFound extends RuntimeException{
    public PostNotFound(String message) {
        super(message);
    }
}
