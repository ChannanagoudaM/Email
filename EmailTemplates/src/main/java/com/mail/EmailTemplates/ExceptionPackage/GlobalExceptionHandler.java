package com.mail.EmailTemplates.ExceptionPackage;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MailNotFound.class)
    public ResponseEntity<String> mailNotFoundResponseEntity(MailNotFound exception)
    {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
