package com.softpro.airlines.model.exceptions;

import org.springframework.http.HttpStatus;

public class AirportNotFoundException extends AppException {
    public final String name;
    public final Long id;

    public AirportNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND);
        this.name = name;
        this.id = null;
    }

    public AirportNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND);
        this.name = null;
        this.id = id;
    }
}
