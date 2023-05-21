package com.example.family.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FamilyControllerAdvice {

    @ExceptionHandler(Exception.class)
    public HttpEntity<ErrorDto> exceptionHandler(Exception e) {
        Throwable firstException = e;

        while (firstException.getCause() != null) {
            firstException = firstException.getCause();
        }

        return new ResponseEntity<>(new ErrorDto(firstException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
