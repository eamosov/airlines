package com.softpro.airlines.model.exceptions;

import org.springframework.http.HttpStatus;

public class FlightNotFoundException extends AppException{
    public final long id;

    public FlightNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND);
        this.id = id;
    }
}
