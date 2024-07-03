package com.blogapp2.exception;

public class ResourceNotFound  extends  RuntimeException{


    public ResourceNotFound(String message) {
        super(message);
    }
}
