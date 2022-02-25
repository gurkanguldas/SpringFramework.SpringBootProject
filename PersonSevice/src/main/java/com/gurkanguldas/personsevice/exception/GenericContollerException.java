package com.gurkanguldas.personsevice.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GenericContollerException
{
    //If the entity is empty, the status should be HttpEntity.NOT_FOUND.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map> notFundException(EntityNotFoundException exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prepareResponse(exception.getMessage()));
    }

    //If the entity has a validation error, the status should be HttpEntity.BAD_REQUEST.
    @ExceptionHandler(EntityValidateException.class)
    public ResponseEntity<Map> validateException(EntityValidateException exception)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(prepareResponse(exception.getMessage()));
    }

    //Mapping Response Messages
    private Map<String, String> prepareResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
