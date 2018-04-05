package com.softpro.airlines.model.exceptions;

import org.springframework.http.HttpStatus;

public class RouteNotFoundException extends AppException{
    public final long id;

    public RouteNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND);
        this.id = id;
    }
}
