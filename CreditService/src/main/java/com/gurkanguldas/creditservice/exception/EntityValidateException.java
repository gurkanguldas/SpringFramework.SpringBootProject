package com.gurkanguldas.creditservice.exception;

//Throws EntityValidateException If Entity Has Validation Error.
//Use the LogMessage class to learn about validation errors.
public class EntityValidateException extends RuntimeException
{
    public EntityValidateException(String mes)
    {
        super(mes);
    }
}
