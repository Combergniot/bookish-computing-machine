package com.empik.githubapi.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String login){
        super("Could not find user: " + login);
    }
}
