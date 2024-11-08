package com.kunal.blogbackend.exception;

import lombok.Getter;


@Getter
public class ResourceNotFoundException extends RuntimeException{
    
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;
    
    public ResourceNotFoundException(String resourceName,String fieldName, Object fieldValue){
        super("Resource not found : "+ resourceName+" not found with "+fieldName+" : "+fieldValue+" ");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
