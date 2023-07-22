package com.gen.signatureverification.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class DuplicateResourceFoundException extends RuntimeException {

    private String resourceName;
    private String name;
    private String email;

    private LocalTime time;

    public DuplicateResourceFoundException(String resourceName, String name, String email) {
        super(String.format("%s is already exists with name : %s , email : %s", resourceName, name, email));
        this.resourceName = resourceName;
        this.name = name;
        this.email = email;
    }

    public DuplicateResourceFoundException(String resourceName, String name){
        super(String.format("%s already exists with name : %s", resourceName, name));
        this.resourceName = resourceName;
        this.name = name;
    }

    public DuplicateResourceFoundException(String resourceName, LocalTime time){
        super(String.format("%s already checked out at time : %s", resourceName, time));
        this.resourceName = resourceName;
        this.time = time;
    }
}
