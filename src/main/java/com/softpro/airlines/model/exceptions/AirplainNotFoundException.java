package com.softpro.airlines.model.exceptions;

import org.springframework.http.HttpStatus;

public class AirplainNotFoundException extends AppException{
    public final long id;

    public AirplainNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND);
        this.id = id;
    }
}
