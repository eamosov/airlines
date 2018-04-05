package com.softpro.airlines.model.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class AppException extends Exception {
    public final HttpStatus httpStatus;
    public final String error;

    public AppException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.error = this.getClass().getSimpleName();
    }

    @Override
    @JsonIgnore
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    @JsonIgnore
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    @JsonIgnore
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
