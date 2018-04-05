package com.softpro.airlines.controllers;

import com.softpro.airlines.model.exceptions.AppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    protected ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex, new HttpHeaders(), ex.httpStatus, request);
    }

}
