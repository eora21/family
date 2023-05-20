package com.example.family.advice;

import com.example.family.domain.ErrorDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.family.controller")
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public HttpEntity<ErrorDto> exceptionHandler(Exception e) {
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
