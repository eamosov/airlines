package com.softpro.airlines.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Аэропорт
 */
@Entity
public class Airport {

    @Id
    private long id;

    private String name;

    public Airport() {
    }

    public Airport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
