package com.gurkanguldas.personsevice.exception;

public class EntityNotFoundException extends RuntimeException
{
    //Throw EntityNotFoundException If The Entity Is Empty.
    public EntityNotFoundException(String entity)
    {
        super("Related " + entity + " not found.");
    }
}
