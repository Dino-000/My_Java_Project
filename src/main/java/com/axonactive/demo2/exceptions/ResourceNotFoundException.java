package com.axonactive.demo2.exceptions;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
