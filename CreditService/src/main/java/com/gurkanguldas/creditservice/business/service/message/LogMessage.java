package com.gurkanguldas.creditservice.business.service.message;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class LogMessage
{
    // Generates message for EntityValidationException
    public String validationMessage(BindingResult bindingResult)
    {
        String errors = "";
        if (bindingResult.hasErrors())
        {
            for (ObjectError error : bindingResult.getAllErrors())
            {
                errors += "defaultMessage: " + error.getDefaultMessage()+" ";
                errors += "objectName: " + error.getObjectName()+" ";
                errors += "field: " + error.getCodes()[0]+" ";
            }
        }
        return errors;
    }
}
