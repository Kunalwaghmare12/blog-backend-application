package com.kunal.blogbackend.exception;

import lombok.Getter;

@Getter
public class ResourceFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;
    
    public ResourceFoundException(String resourceName,String fieldName, Object fieldValue){
        super("Resource found : "+ resourceName+" found with "+fieldName+" : "+fieldValue+" ");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
