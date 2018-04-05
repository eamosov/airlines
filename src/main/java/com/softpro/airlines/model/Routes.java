package com.softpro.airlines.model;

public enum Routes {
    M1(1, 2),
    M2(3, 4),
    M3(5, 6);

    public final long a;
    public final long b;

    Routes(long a, long b) {
        this.a = a;
        this.b = b;
    }
}

