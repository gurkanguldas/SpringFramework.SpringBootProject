package com.gurkanguldas.frontendservice.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GenericContollerException
{
    //In case of client errors, it sends the HttpStatus.NOT_FOUND response.
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map> notFundException(HttpClientErrorException exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prepareResponse(exception.getMessage()));
    }
    private Map<String, String> prepareResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
