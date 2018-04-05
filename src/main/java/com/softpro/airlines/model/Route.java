package com.softpro.airlines.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Маршрут
 */
@Entity
public class Route {

    /**
     * Уникальный id (номер)
     */
    @Id
    private long id;

    /**
     * Название маршрута
     */
    private String name;

    /**
     * Точки посадки маршрута
     */
    @ManyToMany
    @JoinTable(name = "route_airports",
        joinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "airport_id", referencedColumnName = "id"))
    private List<Airport> lands;

    public Route() {

    }

    public Route(long id, String name, List<Airport> lands) {
        this.id = id;
        this.name = name;
        this.lands = lands;
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

    public List<Airport> getLands() {
        return lands;
    }

    public void setLands(List<Airport> lands) {
        this.lands = lands;
    }

    @Override
    public String toString() {
        return "Route{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lands=" + lands +
            '}';
    }
}
