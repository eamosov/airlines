package com.softpro.airlines.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Самолет
 */
@Entity
public class Airplain {

    /**
     * Уникальный id (номер)
     */
    @Id
    private long id;

    /**
     * Название
     */
    private String name;


    public Airplain() {

    }

    public Airplain(long id, String name) {
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
        return "Airplain{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

